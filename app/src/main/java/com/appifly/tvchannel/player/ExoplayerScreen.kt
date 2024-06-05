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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.Loader
import com.appifly.tvchannel.ui.theme.ScreenOrientation
import com.appifly.tvchannel.ui.theme.darkBackground
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
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    setShowNextButton(false)
                    setShowPreviousButton(false)


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
                                        playerReadyToPlay(
                                            it,
                                            context,
                                            exoPlayer
                                        )
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
                                .clickable { isPLaying.value = false },
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
        if (loading.value) {
            Loader()
        }
    }


    LaunchedEffect(videoUrl.observeAsState().value) {

        videoUrl.value?.liveUrl?.let { playerReadyToPlay(it, context, exoPlayer) }
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
    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
    val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
        context,
        defaultDataSourceFactory
    )
    val source =
        getHlsMediaSource(dataSourceFactory, videoUrl)
    /*  else
      getProgressiveMediaSource(dataSourceFactory, videoUrl.value!!.liveUrl!!)*/

    exoPlayer.setMediaSource(source)
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