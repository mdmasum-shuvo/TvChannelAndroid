package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.StartIoBannerAdView
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.LiveEventCardItem
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.home.gotoChannelDetail

@Composable
fun ChannelScreen(
    viewModel: CategoryViewModel,
    homeViewModel: HomeViewModel,
    channelViewModel: ChannelViewModel, navController: NavController
) {
    val context = LocalContext.current
    Column {
        MainTopBar(onSearchIconClick = { navController.navigate(Routing.SearchScreen.routeName) })

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            AdmobBanner(adLiveData = homeViewModel.adIdData, isAdaptive = true)

            homeViewModel.eventListLiveData.observeAsState().value?.let {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(it) { item ->
                        LiveEventCardItem(item) { channelDto ->
                            gotoChannelDetail(
                                context,
                                channelViewModel,
                                channelDto,
                                navController,
                                homeViewModel.adIdData
                            )

                        }
                        SpacerHeight(12.dp)
                    }
                }
            }
            StartIoBannerAdView()

        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelScreen() {
    TvChannelTheme {
        //ChannelScreen()
    }
}