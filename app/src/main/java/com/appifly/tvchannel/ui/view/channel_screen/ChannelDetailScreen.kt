package com.appifly.tvchannel.ui.view.channel_screen

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.MainActivity
import com.appifly.tvchannel.R
import com.appifly.tvchannel.player.PlayerScreen
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.GradientFavIcon
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.SpacerWidth
import com.appifly.tvchannel.ui.common_component.TextView12W400
import com.appifly.tvchannel.ui.common_component.TextView14W500
import com.appifly.tvchannel.ui.common_component.TextView18W500
import com.appifly.tvchannel.ui.theme.ScreenOrientation
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.darkBackground
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.theme.lightBackground
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText
import com.appifly.tvchannel.utils.AppUtils.hideSystemUI
import com.appifly.tvchannel.utils.Constants.PLAYER_CONTROLS_VISIBILITY
import com.appifly.tvchannel.utils.setLandscape
import com.appifly.tvchannel.utils.setPortrait
import kotlinx.coroutines.delay


@Composable
fun ChannelDetailScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    activity: Activity = LocalContext.current as MainActivity,
    navController: NavController
) {
    val context = LocalContext.current

    var shouldShowControls by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = shouldShowControls) {
        if (shouldShowControls) {
            delay(PLAYER_CONTROLS_VISIBILITY)
            shouldShowControls = false
        }
    }
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

            MainTopBar(isBackEnable = true, navigateBack = {
                navController.popBackStack()
            }, onSearchIconClick = {navController.navigate(Routing.SearchScreen.routeName) })

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(MaterialTheme.dimens.mediumWeightTv, fill = true)
            ) {
                PlayerScreen(
                    videoUrl = channelViewModel.selectedChannel,
                    isFullScreen = false, navigateBack = {
                        navController.popBackStack()
                    }
                ) {
                    shouldShowControls = shouldShowControls.not()

                }
                this@Column.AnimatedVisibility(
                    modifier = Modifier.fillMaxSize(),
                    visible = shouldShowControls,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier.background(darkBackground.copy(alpha = 0.6f))
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 16.dp, bottom = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .clickable { context.setLandscape() },
                                contentScale = ContentScale.Crop,
                                painter = painterResource(
                                    id = R.drawable.full_screen_entry
                                ),
                                contentDescription = ""
                            )
                        }
                    }
                }
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
                        TextView14W500(
                            value = channelViewModel.selectedChannel.observeAsState().value?.name
                                ?: "N/A"
                        )
                        TextView12W400(
                            value = "${viewModel.channelCategoryName.observeAsState().value} Channel",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
                channelViewModel.isFavoriteChannel.observeAsState().value?.let {
                    GradientFavIcon(
                        size = 24.dp,
                        isFavorite = it
                    ) { isFav ->
                        if (isFav) {
                            channelViewModel.removeFavoriteChannel(channelViewModel.selectedChannel.value?.id!!)
                        } else {
                            channelViewModel.setFavoriteChannel(channelViewModel.selectedChannel.value!!)
                        }
                    }
                }
            }
            SpacerHeight(MaterialTheme.dimens.stdDimen12)

            AdmobBanner()
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(2f, fill = true)
            ) {
                channelViewModel.channelData.observeAsState().value?.let {
                    HeaderText(viewModel.channelCategoryName.observeAsState().value)

                    LazyVerticalGrid(
                        modifier = Modifier.height(((MaterialTheme.dimens.gridItemHeight * it.size) / 2).dp),
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
                        items(items = it, key = {item-> item.id!! }) { item ->
                            RegularChannelItem(
                                item = item,
                                modifier = Modifier.height(MaterialTheme.dimens.channelMedium),
                                onItemClick = { clickedItem ->
                                    channelViewModel.setSelectedChannel(clickedItem)
                                    channelViewModel.checkFavorite(channelViewModel.selectedChannel.value?.id!!)
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

        Box {
            PlayerScreen(
                videoUrl = channelViewModel.selectedChannel,
                isFullScreen = true,
            ) {
                shouldShowControls = shouldShowControls.not()
            }
            AnimatedVisibility(
                modifier = Modifier.fillMaxSize(),
                visible = shouldShowControls,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(modifier = Modifier.fillMaxSize()  .background(darkBackground.copy(alpha = 0.6f))) {
                    Box(
                        modifier = Modifier

                            .align(Alignment.TopStart)
                            .padding(start = 16.dp, top = 16.dp)
                    ) {
                        TextView18W500(
                            value = channelViewModel.selectedChannel.observeAsState().value?.name
                                ?: "N/A",
                            color = lightBackground
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 32.dp, bottom = 32.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .clickable { context.setPortrait() },
                            contentScale = ContentScale.Crop,
                            painter = painterResource(
                                id = R.drawable.full_screen_exit
                            ),
                            contentDescription = ""
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