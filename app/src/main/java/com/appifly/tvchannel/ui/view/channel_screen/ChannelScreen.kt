package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.appifly.tvchannel.ui.theme.TvChannelTheme

/*
@Composable
fun ChannelScreen(
    viewModel: CategoryViewModel,
    homeViewModel: HomeViewModel,
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

            AdmobBanner(adLiveData = homeViewModel.adIdData, isAdaptive = true)

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
                    modifier = Modifier.height((((MaterialTheme.dimens.gridItemHeight + MaterialTheme.dimens.stdDimen12.value + MaterialTheme.dimens.stdDimen12.value) * it.size) / if (it.size < 3) 1 else 3).dp),
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
                                loadInterstitialAdd(context,homeViewModel.adIdData?.value)
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
*/


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelScreen() {
    TvChannelTheme {
        //ChannelScreen()
    }
}