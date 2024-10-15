package com.appifly.tvchannel.ui.view.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.appifly.app_data_source.dto.AdIdDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.app_data_source.viewmodel.SeeAllChannelViewModel
import com.appifly.tvchannel.loadInterstitialAdd
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.TopBannerItem
import com.appifly.tvchannel.ui.theme.dimens

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
        channelViewModel.resetSelectedChannel()
    })
    val selectedIndex = remember { mutableIntStateOf(0) }
    Column {
        MainTopBar(onSearchIconClick = {
            navController.navigate(Routing.SearchScreen.routeName)
        })

        Column(
            modifier = Modifier
                .fillMaxWidth()
               ,
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
                CategoryListSection(it, selectedIndex) { item ->
                    channelViewModel.catId = item.id
                    navController.navigate(Routing.ChannelDetailScreen.routeName)
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