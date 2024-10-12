package com.appifly.tvchannel.tv_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.utils.showToast


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
        Row(modifier = Modifier.padding(58.dp)) {
            LazyColumn(modifier = Modifier.weight(0.3f)) {
                itemsIndexed(it) { index, item ->
                    TvCategoryItem(
                        item.name ?: "",
                        index = index,
                        selectedIndex = selectedIndex,
                        focusRequester = focusRequester
                    ) { value ->
                        channelViewModel.catId = it[index].id
                        channelViewModel.callChannelDataByCatId()
                        context.showToast(value)
                    }
                }

            }

            Box(modifier = Modifier.weight(0.7f)) {
                channelViewModel.channelData.observeAsState().value?.let {
                    context.showToast("Call Data")
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
                            RegularChannelItem(
                                item = item,
                                modifier = Modifier.size(MaterialTheme.dimens.channelMedium),
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