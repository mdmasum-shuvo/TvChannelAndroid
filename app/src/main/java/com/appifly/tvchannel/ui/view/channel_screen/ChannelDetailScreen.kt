package com.appifly.tvchannel.ui.view.channel_screen

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.MainActivity
import com.appifly.tvchannel.player.PlayVideo
import com.appifly.tvchannel.ui.theme.TvChannelTheme


@Composable
fun ChannelDetailScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    homeViewModel: HomeViewModel,
    activity: Activity = LocalContext.current as MainActivity,
    navController: NavController,
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