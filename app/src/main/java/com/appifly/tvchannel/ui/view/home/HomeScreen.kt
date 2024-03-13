package com.appifly.tvchannel.ui.view.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.common_component.FrequentlyPlayedItem
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.TopBannerItem
import com.appifly.tvchannel.ui.common_component.TvSeriesItem
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun HomeScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current
    val selectedIndex = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainTopBar()
        SpacerHeight(height = 16)




        homeViewModel.bannerListLiveData?.observeAsState()?.value?.let {
            TopBannerItem(it)
        }




        viewModel.categoryData?.observeAsState()?.value?.let {

            LaunchedEffect(key1 = true, block = {
                if (it.isNotEmpty()) {
                    channelViewModel.catId = it[0].id
                    channelViewModel.callChannelDataByCatId()
                    viewModel.setCategoryName(it[0].name)
                }
            })
            CategoryListSection(it, selectedIndex) { item ->
                channelViewModel.catId = item.id
                channelViewModel.callChannelDataByCatId()
                viewModel.setCategoryName(item.name)
            }
        }



        if (!channelViewModel.channelData.observeAsState().value.isNullOrEmpty()) {
            channelViewModel.channelData.observeAsState().value?.let {
                Column(horizontalAlignment = Alignment.Start) {
                    HeaderText(
                        viewModel.channelCategoryName.observeAsState().value,
                        context.getString(R.string.see_all)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
                    ) {
                        items(items = it, key = { it.id!! }) { item ->
                            FrequentlyPlayedItem(
                                item,
                                onItemClick = { item -> },
                                onFavClick = { channelId ->
                                    channelViewModel.setFavoriteChannel(channelId)
                                })
                        }
                    }
                }

            }
        }


        /*     item {
                 channelViewModel.channelData.observeAsState().value?.let {
                     Column(horizontalAlignment = Alignment.Start) {

                         HeaderText(
                             context.getString(R.string.frequently_played),
                             context.getString(R.string.see_all)
                         )

                         LazyRow(
                             horizontalArrangement = Arrangement.spacedBy(12.dp),
                             modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
                         ) {
                             items(it) { item ->
                                 FrequentlyPlayedItem(item)
                             }
                         }
                     }

                 }
             }



     */

        if (!channelViewModel.popularChannelList?.observeAsState()?.value.isNullOrEmpty()) {
            channelViewModel.popularChannelList?.observeAsState()?.value?.let {
                Column(horizontalAlignment = Alignment.Start) {
                    HeaderText(
                        context.getString(R.string.popular_channel),
                        context.getString(R.string.see_all)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
                    ) {
                        items(items = it, key = { it.id!! }) { item ->
                            RegularChannelItem(item, onItemClick = {}, onFavClick = { channelId ->
                                channelViewModel.setFavoriteChannel(channelId)

                            })
                        }
                    }
                }

            }
        }




        if (!homeViewModel.tvShowListLiveData?.observeAsState()?.value.isNullOrEmpty()) {
            homeViewModel.tvShowListLiveData?.observeAsState()?.value?.let {
                HeaderText(
                    context.getString(R.string.tv_shows),
                    context.getString(R.string.see_all)
                )
                TvSeriesItem(it)
            }
        }

        if (!channelViewModel.favoriteChannelList.observeAsState().value.isNullOrEmpty()) {
            channelViewModel.favoriteChannelList.observeAsState().value?.let {
                Column(horizontalAlignment = Alignment.Start) {


                    HeaderText(
                        context.getString(R.string.favorites),
                        context.getString(R.string.see_all)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
                    ) {
                        items(items = it, key = { it.id!! }) { item ->
                            FrequentlyPlayedItem(
                                item,
                                isFavorite = true,
                                onItemClick = { item -> },
                                onFavClick = { channelId ->
                                    /*        if (!isFav) {
                                                channelViewModel.setFavoriteChannel(channelId)
                                            }
        */
                                })
                        }
                    }
                }
            }
        }


    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewHomeSceen() {
    TvChannelTheme {
        //  HomeScreen()
    }
}