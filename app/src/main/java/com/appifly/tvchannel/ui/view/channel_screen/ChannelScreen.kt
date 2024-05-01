package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.loadInterstitialAdd
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun ChannelScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel, navController: NavController
) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Column {
        MainTopBar(onSearchIconClick = { navController.navigate(Routing.SearchScreen.routeName) })

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {


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




            channelViewModel.channelData.observeAsState().value?.let {
                HeaderText(viewModel.channelCategoryName.observeAsState().value)

                LazyVerticalGrid(
                    modifier = Modifier.height(((MaterialTheme.dimens.gridItemHeight * it.size) / 2).dp),
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
                            modifier = Modifier.height(MaterialTheme.dimens.channelMedium),
                            onItemClick = { clickedItem ->
                                loadInterstitialAdd(context)
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


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelScreen() {
    TvChannelTheme {
        //ChannelScreen()
    }
}