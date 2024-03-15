package com.appifly.tvchannel.player

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.appifly.tvchannel.ui.common_component.Loader
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


@Composable
fun ExoPlayerScreen(
    url: String? = null,
    modifier: Modifier = Modifier,
) {
    val loading = remember {
        mutableStateOf(true)
    }

    val isPlayFinished = remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)


    var videoUrl = url
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build().also { exoPlayer ->
        exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
            .buildUpon()
            .setMaxVideoSizeSd()
            .build()
    }
    val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl ?: ""))

    exoPlayer.setMediaItem(mediaItem)

    val playerView = StyledPlayerView(context).apply {
        layoutParams =
            FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        useController = false
        RESIZE_MODE_FILL
    }



    LaunchedEffect(key1 = true) {
        playerView.player = exoPlayer
        playerView.player?.repeatMode = Player.REPEAT_MODE_ALL
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }


    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DisposableEffect(AndroidView(factory = { playerView })) {
                val observer = LifecycleEventObserver { owner, event ->
                    when (event) {
                        Lifecycle.Event.ON_PAUSE -> {
                            exoPlayer.pause()
                        }

                        Lifecycle.Event.ON_RESUME -> {
                            exoPlayer.play()
                        }

                        else -> {}
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
        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            super.onTimelineChanged(timeline, reason)

        }

        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            super.onPlayWhenReadyChanged(playWhenReady, reason)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            if (playbackState == ExoPlayer.STATE_ENDED) {
                exoPlayer.stop()
                exoPlayer.release()
                Log.e("player_loading", " state Finished")
                isPlayFinished.value = true
            } else if (playbackState == ExoPlayer.STATE_READY) {
                loading.value = false
                Log.e("player_loading", " state ready")
            }

            if (playbackState == ExoPlayer.STATE_BUFFERING) {
                Log.e("player_loading", " state buffer")
                loading.value = true
            } else {
                Log.e("player_loading", " state buffer done")
                loading.value = false
            }


        }

        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            if (isLoading) {
                Log.e("player_loading", "loading exoplayer")
                // loading.value = isLoading
            }
        }
    })

}