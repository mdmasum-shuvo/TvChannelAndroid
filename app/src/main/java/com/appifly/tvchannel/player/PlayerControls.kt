package com.appifly.tvchannel.player

import android.util.Log
import android.view.KeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player.STATE_BUFFERING
import androidx.tv.material3.IconButton
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.Loader

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    playbackState: () -> Int,
    getTitle: () -> String,
    onPauseToggle: () -> Unit,
    onPrevious: () -> Unit,
    isFullScreen: Boolean = true
) {
    val visible = remember(isVisible()) { isVisible() }
    val playing = remember(isPlaying()) { isPlaying() }
    val title = remember(getTitle()) { getTitle() }
    val playerState = remember(playbackState()) { playbackState() }

    // Create a FocusRequester
    val playPauseFocusRequester = remember { FocusRequester() }

    LaunchedEffect(visible) {
        if (visible) {
            playPauseFocusRequester.requestFocus()  // Request focus when controls are visible
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            modifier = modifier,
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f))
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                val controlButtonModifier: Modifier = if (isFullScreen) Modifier
                    .padding(horizontal = 8.dp)
                    .size(40.dp) else Modifier.size(32.dp)

                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    horizontalArrangement = if (isFullScreen) Arrangement.Center else Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        modifier = controlButtonModifier,
                        onClick = onPrevious
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = androidx.media3.ui.R.drawable.exo_ic_skip_previous),
                            contentDescription = null
                        )
                    }



                    IconButton(
                        modifier = controlButtonModifier
                            .focusRequester(playPauseFocusRequester) // Apply FocusRequester
                            .focusable()
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.type == KeyEventType.KeyDown &&
                                    (keyEvent.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                                            keyEvent.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER)
                                ) {
                                    Log.d(
                                        "PlayerControls",
                                        "D-pad Center/Enter detected, toggling play/pause"
                                    )
                                    onPauseToggle()
                                    true
                                } else {
                                    false
                                }
                            },
                        onClick = { onPauseToggle() }
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(
                                id = if (playing) R.drawable.pause_button else R.drawable.play_button
                            ),
                            contentDescription = null
                        )
                    }

                }
            }
        }
        if (playing.not() && playerState == STATE_BUFFERING) {
            Loader()
        }
    }
}
