package com.appifly.tvchannel.player

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.ui.common_component.MainTopBar


@Composable
 fun GameVideos(
    playerWrapper: PlayerWrapper,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit,
    channelViewModel: ChannelViewModel
) {

    val configuration = LocalConfiguration.current



    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column {
                MainTopBar(
                    isBackEnable = true,
                    navigateBack = navigateBack,
                    onSearchIconClick = { /*navController.navigate(Routing.SearchScreen.routeName)*/ })
                PotraitView(
                    playerWrapper = playerWrapper,
                    onFullScreenToggle = onFullScreenToggle,
                    navigateBack = navigateBack,
                    channelViewModel = channelViewModel
                )
            }
        }

        else -> {
            LandscapeView(
                playerWrapper = playerWrapper,
                onFullScreenToggle = onFullScreenToggle
            )
        }
    }
}