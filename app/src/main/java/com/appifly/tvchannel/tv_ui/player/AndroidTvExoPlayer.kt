package com.appifly.tvchannel.tv_ui.player

import androidx.compose.runtime.Composable
import com.appifly.tvchannel.player.LandscapeView
import com.appifly.tvchannel.player.PlayerWrapper

@Composable
fun AndroidTvExoPlayer(
    playerWrapper: PlayerWrapper,
) {
    LandscapeView(
        playerWrapper = playerWrapper,
    )
}