package com.appifly.tvchannel.ui.view.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText


@Composable
fun FavoriteChannelListScreen(
    channelViewModel: ChannelViewModel, navController: NavController
) {
    val context = LocalContext.current
    Column {
        MainTopBar(isBackEnable = true, navigateBack = {
            navController.navigateUp()
        }, onSearchIconClick = { navController.navigate(Routing.SearchScreen.routeName) })
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!channelViewModel.favoriteChannelList.observeAsState().value.isNullOrEmpty()) {
                channelViewModel.favoriteChannelList.observeAsState().value?.let {
                    Column(horizontalAlignment = Alignment.Start) {

                        HeaderText(
                            context.getString(R.string.favorites),
                            ""
                        )

                        LazyVerticalGrid(
                            modifier = Modifier.height(((MaterialTheme.dimens.gridItemHeight * it.size) / if (it.size<3) 1 else 3).dp),
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
                                    isFavoriteItem = true,
                                    modifier = Modifier.height(MaterialTheme.dimens.channelMedium),
                                    onItemClick = { clickedItem ->
                                        channelViewModel.setSelectedChannel(clickedItem)
                                        navController.navigate(Routing.ChannelDetailScreen.routeName)
                                    },
                                    onFavClick = { id ->
                                        channelViewModel.removeFavoriteChannel(id)
                                    }
                                )
                            }

                        }
                    }
                }
            }

        }
    }
}