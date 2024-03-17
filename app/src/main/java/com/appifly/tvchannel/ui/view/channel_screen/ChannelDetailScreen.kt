package com.appifly.tvchannel.ui.view.channel_screen

import android.app.Activity
import android.content.res.Configuration
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.MainActivity
import com.appifly.tvchannel.player.ExoPlayerScreen
import com.appifly.tvchannel.ui.common_component.GradientFavIcon
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.SpacerWidth
import com.appifly.tvchannel.ui.common_component.TextView12_W400
import com.appifly.tvchannel.ui.common_component.TextView14_W500
import com.appifly.tvchannel.ui.theme.ScreenOrientation
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun ChannelDetailScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    activity: Activity = LocalContext.current as MainActivity,
) {

    LaunchedEffect(key1 = true, block = {
        channelViewModel.catId = channelViewModel.selectedChannel.value?.catId!!
        channelViewModel.callChannelDataByCatId()
        channelViewModel.checkFavorite(channelViewModel.selectedChannel.value?.id!!)
        viewModel.getCategoryNameById(channelViewModel.catId)
    })
    if (ScreenOrientation == Configuration.ORIENTATION_PORTRAIT) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            MainTopBar(isBackEnable = true)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(MaterialTheme.dimens.mediumWeightTv, fill = true)
            ) {
                ExoPlayerScreen(
                    videoUrl = channelViewModel.selectedChannel
                )
                //http://ert-live-bcbs15228.siliconweb.com/media/ert_world/ert_worldmedium.m3u8
                //https://mediashohayprod-aase.streaming.media.azure.net/26a9dc05-ea5b-4f23-a3bb-cc48d96e605b/video-24-1687293003062-media-24.ism/manifest(format=m3u8-aapl)
            }
            SpacerHeight(MaterialTheme.dimens.stdDimen12)
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    RegularChannelItem(
                        modifier = Modifier.size(MaterialTheme.dimens.channelExtraSmall),
                        item = ChannelDto(iconUrl = channelViewModel.selectedChannel.observeAsState().value?.iconUrl)
                    )
                    SpacerWidth(MaterialTheme.dimens.stdDimen10)

                    Column {
                        TextView14_W500(
                            value = channelViewModel.selectedChannel.observeAsState().value?.name
                                ?: "N/A"
                        )
                        TextView12_W400(
                            value = "This is channel detail",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }

                GradientFavIcon(
                    size = 24.dp,
                    isFavorite = channelViewModel.isFavoriteChannel.observeAsState().value!!
                ) { isFav ->
                    if (isFav) {
                        channelViewModel.removeFavoriteChannel(channelViewModel.selectedChannel.value?.id!!)
                    }else{
                        channelViewModel.setFavoriteChannel(channelViewModel.selectedChannel.value!!)

                    }
                }

            }
            SpacerHeight(MaterialTheme.dimens.stdDimen12)

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(2f, fill = true)
            ) {
                channelViewModel.channelData.observeAsState().value?.let {
                    HeaderText(viewModel.channelCategoryName.observeAsState().value)

                    LazyVerticalGrid(
                        modifier = Modifier.height(((112 * 10) / 2).dp),
                        columns = GridCells.Fixed(MaterialTheme.dimens.gridCellsChannel),
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
                                modifier = Modifier.height(MaterialTheme.dimens.channelMedium),
                                onItemClick = { clickedItem ->
                                    channelViewModel.setSelectedChannel(clickedItem)
                                    channelViewModel.addTOFrequentChannel(clickedItem.id!!)
                                },
                            )
                        }

                    }
                }

            }


        }
    } else {
        hideSystemUI(activity)

        ExoPlayerScreen(
            videoUrl = channelViewModel.selectedChannel
        )
    }
}

private fun hideSystemUI(activity: Activity) {
    val decorView = activity.window.decorView
    val uiOptions = decorView.systemUiVisibility
    var newUiOptions = uiOptions
    newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
    newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
    newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
    newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    decorView.systemUiVisibility = newUiOptions
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelDetailScreen() {
    TvChannelTheme {
        //ChannelDetailScreen()
    }
}