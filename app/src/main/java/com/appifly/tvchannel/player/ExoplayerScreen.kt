package com.appifly.tvchannel.player

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
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
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
import androidx.media3.ui.PlayerView
import com.appifly.tvchannel.ui.common_component.Loader



@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerScreen(
    videoUrl: LiveData<String>,

    modifier: Modifier = Modifier,
) {
    val loading = remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current

    val isPlayFinished = remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    // Set up observer for video URI changes

    DisposableEffect(key1 = Unit) {
        // Clean up the ExoPlayer when the composable is disposed
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                useController = false
                RESIZE_MODE_FILL
            }
        },
    )

    LaunchedEffect(videoUrl.observeAsState().value) {
        if (videoUrl.value != null) {
            val defaultDataSourceFactory = DefaultDataSource.Factory(context)
            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                context,
                defaultDataSourceFactory
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(videoUrl.value)))

           exoPlayer.setMediaSource(source)
           // val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl.value))
           // exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DisposableEffect(key1 = Unit) {
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