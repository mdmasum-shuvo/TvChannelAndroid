package com.appifly.tvchannel.player

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay


@Composable
fun LandscapeView(
    playerWrapper: PlayerWrapper,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        PlayerView(
            modifier = Modifier.fillMaxSize(),
            playerWrapper = playerWrapper,

            )
    }
}

fun getGridSize(size: Int): Int {
    return if (size % 3 == 0) 3 else if (size <= 2) 1 else size - 2
}


@Composable
fun PlayerView(
    modifier: Modifier = Modifier,
    playerWrapper: PlayerWrapper,
    onTrailerChange: ((Int) -> Unit)? = null,

    ) {


    Box(modifier = modifier) {

        var shouldShowControls by remember { mutableStateOf(false) }

        var isPlaying by remember { mutableStateOf(playerWrapper.exoPlayer.isPlaying) }

        var playbackState by remember { mutableIntStateOf(playerWrapper.exoPlayer.playbackState) }

        var title by remember {
            mutableStateOf(playerWrapper.exoPlayer.currentMediaItem?.mediaMetadata?.displayTitle.toString())
        }

        var videoTimer by remember { mutableLongStateOf(0L) }

        var totalDuration by remember { mutableLongStateOf(0L) }

        var bufferedPercentage by remember { mutableIntStateOf(0) }

        LaunchedEffect(key1 = shouldShowControls) {
            if (shouldShowControls) {
                delay(4000)
                shouldShowControls = false
            }
        }



        DisposableEffect(key1 = true) {
            val listener = object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    isPlaying = player.isPlaying
                    totalDuration = player.duration
                    videoTimer = player.contentPosition
                    bufferedPercentage = player.bufferedPercentage
                    playbackState = player.playbackState
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    onTrailerChange?.invoke(playerWrapper.exoPlayer.currentPeriodIndex)
                    title = mediaItem?.mediaMetadata?.displayTitle.toString()
                }
            }

            playerWrapper.exoPlayer.addListener(listener)

            onDispose {
                playerWrapper.exoPlayer.removeListener(listener)
            }
        }

        VideoPlayer(
            modifier = Modifier.fillMaxSize(),
            playerWrapper = playerWrapper,
            onPlayerClick = {
                shouldShowControls = shouldShowControls.not()
            }
        )

        PlayerControls(
            modifier = Modifier.fillMaxSize(),
            isVisible = { shouldShowControls },
            isPlaying = { isPlaying },
            playbackState = { playbackState },
            getTitle = { title },
            onPrevious = { playerWrapper.exoPlayer.seekToPrevious() },

            onPauseToggle = {
                when {
                    playerWrapper.exoPlayer.isPlaying -> {
                        playerWrapper.exoPlayer.pause()
                    }

                    playerWrapper.exoPlayer.isPlaying.not() && playbackState == STATE_ENDED -> {
                        playerWrapper.exoPlayer.seekTo(0)
                        playerWrapper.exoPlayer.playWhenReady = true
                    }

                    else -> {
                        playerWrapper.exoPlayer.play()
                    }
                }
                isPlaying = isPlaying.not()
            },
        )

    }

}


@OptIn(UnstableApi::class)
@Composable
private fun VideoPlayer(
    modifier: Modifier = Modifier,
    playerWrapper: PlayerWrapper,
    onPlayerClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clickable {
                onPlayerClick.invoke()
            }
            .background(MaterialTheme.colorScheme.background)
            .testTag("VideoPlayerParent")

    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = playerWrapper.exoPlayer
                    useController = false

                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    keepScreenOn = true
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

                }
            })
    }
}