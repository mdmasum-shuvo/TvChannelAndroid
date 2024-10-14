package com.appifly.tvchannel.tv_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.tv_ui.component.TvCategoryItem
import com.appifly.tvchannel.ui.common_component.LargeChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerWidth
import com.appifly.tvchannel.ui.theme.dimens


@Composable
fun TvHomeScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    channelViewModel: ChannelViewModel,
    navController: NavController
) {

    val focusRequester = remember { FocusRequester() }
    val selectedIndex = remember { mutableIntStateOf(0) }

    val context = LocalContext.current


    categoryViewModel.categoryData?.observeAsState()?.value?.let {
        Row(modifier = Modifier.padding(24.dp)) {
            LazyColumn(modifier = Modifier.weight(0.3f)) {
                item {
                    TvCategoryItem(
                       "All",
                    ) { value ->
                        channelViewModel.catId =0
                        channelViewModel.callChannelDataByCatId()
                    }
                }
                itemsIndexed(it) { index, item ->
                    TvCategoryItem(
                        item.name ?: "",
                    ) { value ->
                        channelViewModel.catId = it[index].id
                        channelViewModel.callChannelDataByCatId()
                    }
                }
                item {
                    TvCategoryItem(
                        "Frequently Played",
                    ) { value ->
                        channelViewModel.catId =0
                        channelViewModel.callChannelDataByCatId()
                    }
                }

                item {
                    TvCategoryItem(
                        "Popular Channel",
                    ) { value ->
                        channelViewModel.catId =0
                        channelViewModel.callChannelDataByCatId()
                    }
                }

            }
            SpacerWidth(24.dp)

            Box(modifier = Modifier.weight(0.8f)) {
                channelViewModel.channelData.observeAsState().value?.let {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(MaterialTheme.dimens.gridCellsChannel),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.stdDimen12),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.stdDimen12),
                        userScrollEnabled = false,
                        contentPadding = PaddingValues(
                            start = 12.dp,
                            top = 10.dp,
                            end = 12.dp,
                            bottom = 16.dp
                        )
                    ) {
                        items(items = it, key = { item -> item.id!! }) { item ->
                            LargeChannelItem(
                                item = item,
                                onItemClick = { clickedItem ->
                                    channelViewModel.addTOFrequentChannel(clickedItem.id!!)
                                    channelViewModel.setSelectedChannel(clickedItem)
                                    navController.navigate(Routing.ChannelDetailScreen.routeName)

                                },
                            )
                        }

                    }
                }

            }

        }
    }

}