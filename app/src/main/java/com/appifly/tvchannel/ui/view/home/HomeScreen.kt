package com.appifly.tvchannel.ui.view.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.appifly.app_data_source.dto.AdIdDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.app_data_source.viewmodel.SeeAllChannelViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.loadInterstitialAdd
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.common_component.LargeChannelItem
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.TopBannerItem
import com.appifly.tvchannel.ui.common_component.TvSeriesItem
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    homeViewModel: HomeViewModel,
    seeAllChannelViewModel: SeeAllChannelViewModel
) {
    val context = LocalContext.current
    val permission = Manifest.permission.POST_NOTIFICATIONS
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {

    }


    LaunchedEffect(key1 = true, block = {
        checkAndRequestCameraPermission(context, permission, launcher)
    })
    val selectedIndex = remember { mutableIntStateOf(0) }
    Column {
        MainTopBar(onSearchIconClick = {
            navController.navigate(Routing.SearchScreen.routeName)
        })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {

            SpacerHeight(height = MaterialTheme.dimens.stdDimen16)

            homeViewModel.bannerListLiveData?.observeAsState()?.value?.let {
                TopBannerItem(it) { clickedItem ->
                    gotoChannelDetail(context,channelViewModel, clickedItem, navController,homeViewModel.adIdData)
                }
            }
            SpacerHeight(height = MaterialTheme.dimens.stdDimen16)
            AdmobBanner(adLiveData = homeViewModel.adIdData)
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
                        ) {
                            channelViewModel.setSeeAllChannelList(
                                it,
                                context.getString(R.string.all_channel)
                            )
                            navController.navigate(Routing.SeeAllChannelScreen.routeName)

                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 10.dp,
                                bottom = MaterialTheme.dimens.stdDimen24
                            )
                        ) {
                            items(items = it, key = { it.id!! }) { item ->
                                LargeChannelItem(
                                    item,
                                ) { clickedItem ->
                                    gotoChannelDetail(context,
                                        channelViewModel,
                                        clickedItem,
                                        navController,homeViewModel.adIdData
                                    )

                                }
                            }
                        }
                    }

                }
            }

            if (!channelViewModel.frequentlyPlayedChannelList.observeAsState().value.isNullOrEmpty()) {
                channelViewModel.frequentlyPlayedChannelList.observeAsState().value?.let {
                    Column(horizontalAlignment = Alignment.Start) {
                        HeaderText(
                            context.getString(R.string.frequently_played),
                            context.getString(R.string.see_all)
                        ) {
                            channelViewModel.setSeeAllChannelList(
                                it,
                                context.getString(R.string.frequently_played)
                            )
                            navController.navigate(Routing.SeeAllChannelScreen.routeName)
                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 10.dp,
                                bottom = MaterialTheme.dimens.stdDimen24
                            )
                        ) {
                            items(items = it, key = { it.id!! }) { item ->
                                LargeChannelItem(
                                    item,
                                    onItemClick = { clickedItem ->
                                        gotoChannelDetail(context,
                                            channelViewModel,
                                            clickedItem,
                                            navController,homeViewModel.adIdData
                                        )

                                    },
                                )
                            }
                        }
                    }
                }
            }
            AdmobBanner(adLiveData = homeViewModel.adIdData, isAdaptive = true)

            if (!channelViewModel.popularChannelList?.observeAsState()?.value.isNullOrEmpty()) {
                channelViewModel.popularChannelList?.observeAsState()?.value?.let {
                    Column(horizontalAlignment = Alignment.Start) {
                        HeaderText(
                            context.getString(R.string.popular_channel),
                            context.getString(R.string.see_all)
                        ) {
                            channelViewModel.setSeeAllChannelList(
                                it,
                                context.getString(R.string.popular_channel)
                            )
                            navController.navigate(Routing.SeeAllChannelScreen.routeName)

                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 10.dp,
                                bottom = MaterialTheme.dimens.stdDimen24
                            )
                        ) {
                            items(items = it, key = { it.id!! }) { item ->
                                RegularChannelItem(item, onItemClick = { clickedItem ->
                                    gotoChannelDetail(context,
                                        channelViewModel,
                                        clickedItem,
                                        navController,homeViewModel.adIdData
                                    )

                                })
                            }
                        }
                    }
                }
            }
            AdmobBanner(adLiveData = homeViewModel.adIdData)

            if (!homeViewModel.tvShowListLiveData?.observeAsState()?.value.isNullOrEmpty()) {
                homeViewModel.tvShowListLiveData?.observeAsState()?.value?.let {
                    HeaderText(
                        context.getString(R.string.tv_shows),
                        ""
                    )
                    TvSeriesItem(it){ clickedItem ->
                        gotoChannelDetail(context,channelViewModel, clickedItem, navController,homeViewModel.adIdData)
                    }
                }
            }

            if (!channelViewModel.favoriteChannelList.observeAsState().value.isNullOrEmpty()) {
                channelViewModel.favoriteChannelList.observeAsState().value?.let {
                    Column(horizontalAlignment = Alignment.Start) {
                        HeaderText(
                            context.getString(R.string.favorites),
                            context.getString(R.string.see_all)
                        ) {
                            channelViewModel.setSeeAllChannelList(
                                it,
                                context.getString(R.string.favorites)
                            )
                            navController.navigate(Routing.SeeAllChannelScreen.routeName)
                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 10.dp,
                                bottom = MaterialTheme.dimens.stdDimen24
                            )
                        ) {
                            items(items = it, key = { it.id!! }) { item ->
                                LargeChannelItem(
                                    item,
                                    onItemClick = { clickedItem ->
                                        gotoChannelDetail(context,
                                            channelViewModel,
                                            clickedItem,
                                            navController,homeViewModel.adIdData
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun gotoChannelDetail(context: Context,
    channelViewModel: ChannelViewModel,
    clickedItem: ChannelDto,
    navController: NavController,liveData: LiveData<List<AdIdDto>>?
) {

    loadInterstitialAdd(context,liveData?.value)
    channelViewModel.addTOFrequentChannel(clickedItem.id!!)
    channelViewModel.setSelectedChannel(clickedItem)
    navController.navigate(Routing.ChannelDetailScreen.routeName)
}


fun checkAndRequestCameraPermission(
    context: Context,
    permission: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
    } else {
        // Request a permission
        launcher.launch(permission)
    }
}