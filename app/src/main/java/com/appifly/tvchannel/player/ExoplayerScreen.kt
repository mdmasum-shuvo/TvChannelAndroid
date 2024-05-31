package com.appifly.tvchannel.player

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LiveData
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.ARTWORK_DISPLAY_MODE_FILL
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.ui.common_component.Loader
import com.appifly.tvchannel.ui.theme.darkBackground
import com.appifly.tvchannel.utils.setPortrait


@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    videoUrl: LiveData<ChannelDto>,
    isFullScreen: Boolean,
    navigateBack: (() -> Unit)? = null,
    onPlayerClick: () -> Unit = {}

) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    DisposableEffect(Unit) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
    BackHandler {
        if (isFullScreen)
            context.setPortrait()
        else
            navigateBack?.invoke()

    }
    val loading = remember {
        mutableStateOf(true)
    }

    val isPlayFinished = remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }


    AndroidView(
        modifier = Modifier.clickable { onPlayerClick() },
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                useController = false
                ARTWORK_DISPLAY_MODE_FILL
            }
        },
    )

    LaunchedEffect(videoUrl.observeAsState().value) {
        if (videoUrl.value != null) {
         /*   val defaultDataSourceFactory = DefaultDataSource.Factory(context)
            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                context,
                defaultDataSourceFactory
            )*/
     /*       val dataSourceFactory = DefaultHttpDataSource.Factory()
                .setDefaultRequestProperties(mapOf("Referer" to "https://stylisheleg4nt.com/"))
*/
            //https://retosbd.com/tsports/master.php?id=live-02
            val url = "https://www.retosprime.com/star/master.php/stream.m3u8?id=423"
            //https://retosbd.com/toffe/master.php?id=match-1
            //https://www.retosprime.com/star/master.php?id=423
            //https://site.flixtronic.top/ftsx.php?id=2
           // val mediaItem = MediaItem.Builder().setUri(Uri.parse(url)).build()
            //  val source = if (videoUrl.value!!.liveUrl!!.contains("m3u8"))
            val httpDataSourceFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory()
               // .setDefaultRequestProperties(mapOf("Referer" to "https://stylisheleg4nt.com/.m3u8"))

            val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)

            // Create a media item
            val mediaItem = MediaItem.fromUri(Uri.parse(url))

           // val source   =   getHlsMediaSource(dataSourceFactory, url)
            // else
           // val source = getProgressiveMediaSource(dataSourceFactory, url)

            exoPlayer.setMediaSource(mediaSourceFactory.createMediaSource(mediaItem))
            exoPlayer.prepare()
            exoPlayer.play()


        }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader()
            }
        }
    }

    exoPlayer.addListener(object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when (playbackState) {
                ExoPlayer.STATE_ENDED -> {
                    exoPlayer.stop()
                    exoPlayer.release()
                    Log.e("player_loading", " state Finished")
                    isPlayFinished.value = true
                }

                ExoPlayer.STATE_READY -> {
                    loading.value = false
                }

                ExoPlayer.STATE_BUFFERING -> {
                    loading.value = true

                }

                else -> {
                    loading.value = false
                }
            }
        }

    })

}


@OptIn(UnstableApi::class)
private fun getHlsMediaSource(
    dataSourceFactory: DataSource.Factory,
    mediaUrl: String
): MediaSource {
    // Create a HLS media source pointing to a playlist uri.
    val mediaItem = MediaItem.Builder().setUri(Uri.parse(mediaUrl)).build()
    return DefaultMediaSourceFactory(dataSourceFactory).createMediaSource(mediaItem)
}

@UnstableApi
private fun getProgressiveMediaSource(
    dataSourceFactory: DataSource.Factory,
    mediaUrl: String
): MediaSource {
    val mediaItem = MediaItem.Builder().setDrmConfiguration(
        MediaItem.DrmConfiguration.Builder(C.CLEARKEY_UUID).setLicenseUri("0d2418cc6a29a3516d1854ab2973f25f:4979b73603b430465aa4e7bbdefbc4cc").build()
    ).setUri(mediaUrl).build()
    // Create a Regular media source pointing to a playlist uri.
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(mediaItem)
}