package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.player.PlayVideo
import com.appifly.tvchannel.ui.theme.TvChannelTheme


@Composable
fun ChannelDetailScreen(
    channelViewModel: ChannelViewModel,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit
) {


    PlayVideo(onFullScreenToggle = onFullScreenToggle, navigateBack = navigateBack, channelViewModel = channelViewModel)

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelDetailScreen() {
    TvChannelTheme {
        //ChannelDetailScreen()
    }
}