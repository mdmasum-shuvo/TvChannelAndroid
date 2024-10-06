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
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.navigation.NavController
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.utils.Constants.PLAYER_SEEK_BACK_INCREMENT
import com.appifly.tvchannel.utils.Constants.PLAYER_SEEK_FORWARD_INCREMENT


@OptIn(UnstableApi::class)
@Composable
fun PlayVideo(
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navController: NavController,
    navigateBack: () -> Unit,
    channelViewModel: ChannelViewModel,
    isSeeAll:Boolean=false,

    ) {
    val context = LocalContext.current
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

    TvPlayer(
        playerWrapper = PlayerWrapper(exoPlayer),
        onFullScreenToggle = onFullScreenToggle,
        navigateBack = navigateBack,
        isSeeAll =isSeeAll,
        channelViewModel = channelViewModel, navController = navController,
    )
}

@OptIn(UnstableApi::class)
fun playerReadyToPlay(data: ChannelDto, exoPlayer: ExoPlayer) {
    val httpDataSourceFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory()
    data.liveChannelReferer?.let {
        httpDataSourceFactory.setDefaultRequestProperties(mapOf("Referer" to it))
    }
    val mediaItem = MediaItem.Builder().setUri(data.liveUrl).setMediaId(data.id.toString())
        .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(data.name).build())
        .build()
    val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)
    // Create a media item
    // val mediaItem = MediaItem.fromUri(Uri.parse(data.liveUrl))
    exoPlayer.setMediaSource(mediaSourceFactory.createMediaSource(mediaItem))
    exoPlayer.prepare()
    exoPlayer.play()
}
