package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.player.ExoPlayerScreen
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun ChannelDetailScreen(viewModel: CategoryViewModel, channelViewModel: ChannelViewModel) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        channelViewModel.catId = 1
        channelViewModel.callChannelDataByCatId()
        viewModel.setCategoryName("Name")
    })
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainTopBar(isBackEnable = true)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.9f, fill = true)
        ) {
            ExoPlayerScreen(
                videoUrl = viewModel.videoUrl
            )
            //http://ert-live-bcbs15228.siliconweb.com/media/ert_world/ert_worldmedium.m3u8
            // https://mediashohayprod-aase.streaming.media.azure.net/26a9dc05-ea5b-4f23-a3bb-cc48d96e605b/video-24-1687293003062-media-24.ism/manifest(format=m3u8-aapl)
        }

        Column(
            modifier = Modifier
                .weight(2f, fill = true)
        ) {
            channelViewModel.channelData.observeAsState().value?.let {
                HeaderText(viewModel.channelCategoryName.observeAsState().value)

                LazyVerticalGrid(
                    modifier = Modifier.height(((112 * 10) / 2).dp),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false,
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 10.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    )
                ) {
                    items(items = it, key = { it.id!! }) { item ->
                        RegularChannelItem(
                            item = item,
                            modifier = Modifier.height(100.dp),
                            onItemClick = { item ->
                                viewModel.setUrl(item.liveUrl!!)
                                channelViewModel.addTOFrequentChannel(item.id!!)
                            },
                        )
                    }

                }
            }

        }


    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelDetailScreen() {
    TvChannelTheme {
        //ChannelDetailScreen()
    }
}