package com.appifly.tvchannel.ui.view.favorite

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.TextView12W400
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.theme.lightBackground
import com.appifly.tvchannel.ui.view.home.gotoChannelDetail
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun FavoriteScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    categoryViewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel
) {


    LaunchedEffect(key1 = channelViewModel.favoriteChannelList.observeAsState().value, block = {
        if (!channelViewModel.favoriteChannelList.value.isNullOrEmpty()) {
            channelViewModel.favoriteChannelList.value?.let {
                categoryViewModel.setFavoriteCategory(
                    it
                )
            }
        }
    })
    val context = LocalContext.current
    Column {
        MainTopBar(onSearchIconClick = {navController.navigate(Routing.SearchScreen.routeName) })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpacerHeight(height = MaterialTheme.dimens.stdDimen16)
            AdmobBanner(adLiveData = homeViewModel.adIdData, isAdaptive = true)
            HeaderText(title = context.getString(R.string.favorites_screen_title))
            categoryViewModel.favoriteCategoryList.observeAsState().value?.let { list ->
                LazyVerticalGrid(
                    modifier = Modifier.height(((MaterialTheme.dimens.gridFavCatHeight * list.size) /if (list.size<2) 1 else 2).dp),
                    columns = GridCells.Fixed(MaterialTheme.dimens.gridCellsFavorite),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false,
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 4.dp,
                        end = 16.dp,
                        bottom = 4.dp
                    )
                ) {
                    items(list, key = { item -> item.id }) { item ->

                        Column {
                            Card(
                                modifier = Modifier.clickable { navController.navigate(Routing.FavoriteChannelListScreen.routeName) },
                                shape = MaterialTheme.shapes.medium,
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            ) {
                                LazyVerticalGrid(
                                    modifier = Modifier.height(((MaterialTheme.dimens.gridFavHeight * 4) / 2).dp),
                                    columns = GridCells.Fixed(2),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp),
                                    userScrollEnabled = false,
                                    contentPadding = PaddingValues(
                                        start = 8.dp,
                                        top = 8.dp,
                                        end = 8.dp,
                                        bottom = 4.dp
                                    )
                                ) {
                                    val favListOfCat = ArrayList<ChannelDto>()
                                    if (!channelViewModel.favoriteChannelList.value.isNullOrEmpty()) {
                                        for (data in channelViewModel.favoriteChannelList.value!!) {
                                            if (item.id == data.catId) {
                                                favListOfCat.add(data)
                                            }
                                        }

                                        items(favListOfCat) {
                                            RegularChannelItem(
                                                item = it,
                                                modifier = Modifier.height(MaterialTheme.dimens.channelSmall),
                                                borderC = lightBackground,
                                                cardColor = lightBackground,
                                                onItemClick = { clickedItem ->
                                                    gotoChannelDetail(context,
                                                        channelViewModel,
                                                        clickedItem,
                                                        navController,homeViewModel.adIdData
                                                    )

                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            TextView12W400(
                                value = item.name ?: "N/A",
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }

                }
            }


        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewFavoriteScreen() {
    TvChannelTheme {
        //FavoriteScreen()
    }
}