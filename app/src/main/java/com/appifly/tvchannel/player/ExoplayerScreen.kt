package com.appifly.tvchannel.player

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LiveData
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager
import androidx.media3.exoplayer.drm.DrmSessionManager
import androidx.media3.exoplayer.drm.FrameworkMediaDrm
import androidx.media3.exoplayer.drm.LocalMediaDrmCallback
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.Loader
import com.appifly.tvchannel.ui.theme.ScreenOrientation
import com.appifly.tvchannel.ui.theme.darkBackground
import com.appifly.tvchannel.ui.theme.lightBackground
import com.appifly.tvchannel.utils.Constants
import com.appifly.tvchannel.utils.setLandscape
import com.appifly.tvchannel.utils.setPortrait
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    videoUrl: LiveData<ChannelDto>,
    isFullScreen: Boolean,
    navigateBack: (() -> Unit)? = null,

    ) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    var shouldShowControls by remember { mutableStateOf(false) }


    BackHandler {
        if (isFullScreen)
            context.setPortrait()
        else
            navigateBack?.invoke()

    }
    val loading = remember {
        mutableStateOf(true)
    }

    val isPLaying = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = shouldShowControls) {
        if (shouldShowControls) {
            delay(Constants.PLAYER_CONTROLS_VISIBILITY)
            shouldShowControls = false
        }
    }
    val isPlayFinished = remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }


    Box(contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier.clickable { shouldShowControls = true },
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setShutterBackgroundColor(resources.getColor(R.color.primary, null))
                    useController = false
                    keepScreenOn = true
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
             /*       keepScreenOn = true
                    setShowNextButton(false)
                    setShowPreviousButton(false)
                    exoPlayer.trackSelectionParameters =
                        exoPlayer.trackSelectionParameters.buildUpon()
                            .setMaxVideoSize(360, 360)
                            .setMinVideoSize(360, 360).setMaxVideoBitrate(Integer.MIN_VALUE)
                            .build()*/

                }
            },
        )
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = shouldShowControls,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.background(darkBackground.copy(alpha = 0.6f))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    if (!isPLaying.value)
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    isPLaying.value = true
                                    videoUrl.value?.liveUrl?.let {
                                        clearkeyPlayer(context = context, exoPlayer = exoPlayer)
                                    }
                                },
                            contentScale = ContentScale.Crop,
                            painter = painterResource(
                                id = R.drawable.play_button
                            ),
                            contentDescription = ""
                        ) else
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    isPLaying.value = false

                                },
                            contentScale = ContentScale.Crop,
                            painter = painterResource(
                                id = R.drawable.pause_button
                            ),
                            contentDescription = ""
                        )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                   Row {
                       Icon(
                           Icons.Outlined.Settings,
                           tint = lightBackground,
                           modifier = Modifier

                               .size(24.dp),
                           contentDescription = ""
                       )
                       Spacer(modifier = Modifier.width(8.dp))

                       if (ScreenOrientation == Configuration.ORIENTATION_PORTRAIT)
                           Image(
                               modifier = Modifier
                                   .clickable { context.setLandscape() },
                               contentScale = ContentScale.Crop,
                               painter = painterResource(
                                   id = R.drawable.full_screen_entry
                               ),
                               contentDescription = ""
                           ) else {
                           Image(
                               modifier = Modifier
                                   .clickable { context.setPortrait() },
                               contentScale = ContentScale.Crop,
                               painter = painterResource(
                                   id = R.drawable.full_screen_exit
                               ),
                               contentDescription = ""
                           )


                       }

                   }
                }
            }
        }
        if (loading.value) {
            Loader()
        }
    }


    LaunchedEffect(videoUrl.observeAsState().value) {

        videoUrl.value?.liveUrl?.let { clearkeyPlayer(context=context, exoPlayer = exoPlayer) }
    }

    Box(
        modifier = Modifier
            .background(darkBackground)
    ) {

        DisposableEffect(key1 = Unit) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_PAUSE -> {
                        exoPlayer.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        exoPlayer.play()
                    }

                    else -> {
                        exoPlayer.pause()
                    }
                }
            }
            val lifecycle = lifecycleOwner.value.lifecycle
            lifecycle.addObserver(observer)
            onDispose {
                exoPlayer.stop()
                exoPlayer.release()
                lifecycle.removeObserver(observer)
            }

        }

        if (loading.value) {
            //  Loader()
        }
    }

    exoPlayer.addListener(object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when (playbackState) {
                ExoPlayer.STATE_ENDED -> {
                    loading.value = false
                    exoPlayer.stop()
                    exoPlayer.release()
                    Log.e("player_loading", " state Finished")
                    isPlayFinished.value = true
                    isPLaying.value = false
                    shouldShowControls = true

                }

                ExoPlayer.STATE_READY -> {
                    loading.value = false
                    isPLaying.value = true
                    Log.e("player_loading", " state READY")

                }

                ExoPlayer.STATE_IDLE -> {
                    loading.value = false
                    isPLaying.value = false
                    shouldShowControls = true
                    Log.e("player_loading", " state IDOL")

                }

                ExoPlayer.STATE_BUFFERING -> {
                    loading.value = true
                    isPLaying.value = true
                    Log.e("player_loading", " state BUFFERING")

                }

                else -> {
                    loading.value = false
                }
            }
        }

    })

}

@OptIn(UnstableApi::class)
fun playerReadyToPlay(videoUrl: String, context: Context, exoPlayer: ExoPlayer) {
    val httpDataSourceFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory()

    httpDataSourceFactory.setDefaultRequestProperties(mapOf("Referer" to "https://stylisheleg4nt.com/.m3u8"))
    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
    val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
        context,
        defaultDataSourceFactory
    )
    val source =
        getHlsMediaSource(httpDataSourceFactory, "https://www.retosprime.com/crichd/c.php?id=ptvpk")
    /*  else
      getProgressiveMediaSource(dataSourceFactory, videoUrl.value!!.liveUrl!!)*/

    exoPlayer.setMediaSource(source)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
}
/*
@UnstableApi
fun clearkeyPlayer(context: Context, exoPlayer: ExoPlayer) {
    val drmKey = "A8F+KJEfcSIay8CxH5AEAQ"
    val drmKeyId = "7Lyeb+axRe+2ZY+1z3Qn+A"
    val mpdUrl =
        "https://ssc-extra1-ak.akamaized.net/out/v1/647c58693f1d46af92bd7e69f17912cb/index.mpd"
    val keyString =
        "{\"keys\":[{\"kty\":\"oct\",\"k\":\"$drmKey\",\"kid\":\"$drmKeyId\"}],'type':\"temporary\"}"
    */
/*    val drmKeyBytes = drmKey.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val encodedDrmKey = Base64.encodeToString(drmKeyBytes, Base64.DEFAULT)

    val drmKeyIdBytes = drmKeyId.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val encodedDrmKeyId = Base64.encodeToString(drmKeyIdBytes, Base64.DEFAULT)*//*


    val drmBody = "{keys:[{kty:oct,k:${drmKey},kid:${drmKeyId}}],type:temporary}"

    val httpDataSourceFactory = DefaultHttpDataSource.Factory()
    val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)

    val drmConfiguration = MediaItem.DrmConfiguration.Builder(C.CLEARKEY_UUID)
        .setKeySetId(keyString.toByteArray())
//                    .setKeySetId(byteArrayOf(*drmKeyBytes, *drmKeyIdBytes))
        .build()

    val mediaItem = MediaItem.Builder()
        .setUri(mpdUrl)
        .setDrmConfiguration(drmConfiguration)
        .build()

    exoPlayer.setMediaSource(mediaSourceFactory.createMediaSource(mediaItem))
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
}
*/

@OptIn(UnstableApi::class)
fun clearkeyPlayer(context: Context, exoPlayer: ExoPlayer){
    val drmKey = "A8F+KJEfcSIay8CxH5AEAQ"
    val drmKeyId = "7Lyeb+axRe+2ZY+1z3Qn+A"
    val mpdUrl = "https://ssc-extra1-ak.akamaized.net/out/v1/647c58693f1d46af92bd7e69f17912cb/index.mpd"
   // val drmKeyBytes = drmKey.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
/*    val encodedDrmKey = Base64.encodeToString(drmKeyBytes,
        Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)

    val drmKeyIdBytes = drmKeyId.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val encodedDrmKeyId = Base64.encodeToString(drmKeyIdBytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)*/

    val drmBody = "{\"keys\":[{\"kty\":\"oct\",\"k\":\"${drmKey}\",\"kid\":\"${drmKeyId}\"}],\"type\":\"temporary\"}"


    val dashMediaItem = MediaItem.Builder()
        .setUri(mpdUrl)
        .setMimeType(MimeTypes.APPLICATION_MPD)
        .setMediaMetadata(MediaMetadata.Builder().setTitle("test").build())
        .build()

    val trackSelector = DefaultTrackSelector(context)
    val loadControl = DefaultLoadControl()

    val drmCallback = LocalMediaDrmCallback(drmBody.toByteArray())
    val drmSessionManager = DefaultDrmSessionManager.Builder()
        .setPlayClearSamplesWithoutKeys(true)
        .setMultiSession(false)
        .setKeyRequestParameters(HashMap())
        .setUuidAndExoMediaDrmProvider(C.CLEARKEY_UUID, FrameworkMediaDrm.DEFAULT_PROVIDER)
        .build(drmCallback)

    val customDrmSessionManager: DrmSessionManager = drmSessionManager
    val mediaSourceFactory = DefaultMediaSourceFactory(context)
        .setDrmSessionManagerProvider { customDrmSessionManager }
        .createMediaSource(dashMediaItem)


    exoPlayer.setMediaSource(mediaSourceFactory, true)
    exoPlayer.prepare();
    exoPlayer.play();
}

@OptIn(UnstableApi::class)
fun playerReadyToPlayDrm(videoUrl: String="https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd", keyId: String="7Lyeb+axRe+2ZY+1z3Qn+A", key: String="A8F+KJEfcSIay8CxH5AEAQ" ,context: Context, exoPlayer: ExoPlayer) {

    val httpDataSourceFactory = DefaultHttpDataSource.Factory()
    val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)

    val drmConfiguration = MediaItem.DrmConfiguration.Builder(C.WIDEVINE_UUID)
        .setLicenseUri("https://proxy.uat.widevine.com/proxy?provider=widevine_test") // Dummy URL, Clear Key doesn't use a license URL
        //.setKeySetId(mapOf(keyId to key))
        .build()

    val mediaItem = MediaItem.Builder()
        .setUri(videoUrl)
        .setDrmConfiguration(drmConfiguration)
        .build()

    exoPlayer.setMediaSource(mediaSourceFactory.createMediaSource(mediaItem))
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
}


@OptIn(UnstableApi::class)
private fun getHlsMediaSource(
    dataSourceFactory: DataSource.Factory,
    mediaUrl: String
): MediaSource {
    // Create a HLS media source pointing to a playlist uri.
    return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(mediaUrl))
}

@UnstableApi
private fun getProgressiveMediaSource(
    dataSourceFactory: DataSource.Factory,
    mediaUrl: String
): MediaSource {
    // Create a Regular media source pointing to a playlist uri.
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(MediaItem.fromUri(Uri.parse(mediaUrl)))
}