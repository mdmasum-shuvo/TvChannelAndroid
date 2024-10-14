package com.appifly.tvchannel.ui.view.favorite

/*

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
}*/
