package com.appifly.tvchannel.player

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.utils.Constants.PLAYER_SEEK_BACK_INCREMENT
import com.appifly.tvchannel.utils.Constants.PLAYER_SEEK_FORWARD_INCREMENT


@OptIn(UnstableApi::class)
@Composable
fun PlayVideo(
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit,
    channelViewModel: ChannelViewModel
) {
    val context = LocalContext.current
    /*    val mediaItems = MediaItem.Builder().setUri("https://sfux-ext.sfux.info/hls/chapter/105/1588724110/1588724110.m3u8").setMediaId("1").setTag("tag")
            .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle("test").build())
            .build()*/
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(PLAYER_SEEK_BACK_INCREMENT)
            .setSeekForwardIncrementMs(PLAYER_SEEK_FORWARD_INCREMENT)
            .build()
    }
    LaunchedEffect(channelViewModel.selectedChannel.observeAsState().value) {
        channelViewModel.selectedChannel.value.let {
            if (it != null) {
                playerReadyToPlay(it, exoPlayer)
            }

        }
    }



    DisposableEffect(key1 = Unit) {
        val observer = object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                if (exoPlayer.isPlaying.not()) {
                    exoPlayer.play()
                }
            }

            override fun onStop(owner: LifecycleOwner) {
                exoPlayer.pause()
                super.onStop(owner)
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    GameVideos(
        playerWrapper = PlayerWrapper(exoPlayer),
        onFullScreenToggle = onFullScreenToggle,
        navigateBack = navigateBack,
        channelViewModel = channelViewModel
    )
}


@OptIn(UnstableApi::class)
private fun getHlsMediaSource(
    dataSourceFactory: DataSource.Factory,
    mediaUrl: String
): MediaSource {
    // Create a HLS media source pointing to a playlist uri.
    return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(mediaUrl))
}

@OptIn(UnstableApi::class)
fun playerReadyToPlay(data: ChannelDto, exoPlayer: ExoPlayer) {
    /*    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
        val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
            context,
            defaultDataSourceFactory
        )
        val source =
            getHlsMediaSource(dataSourceFactory, videoUrl)
        *//*  else
      getProgressiveMediaSource(dataSourceFactory, videoUrl.value!!.liveUrl!!)*//*

    exoPlayer.setMediaSource(source)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true*/
    val mediaItems = MediaItem.Builder().setUri(data.liveUrl).setMediaId(data.id.toString()).setTag("tag")
        .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(data.name).build())
        .build()
    exoPlayer.setMediaItem(mediaItems)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
}