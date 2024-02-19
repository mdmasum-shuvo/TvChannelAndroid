package com.appifly.tvchannel.ui.view.category

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.view.category.component.CategoryItem


@Composable
fun CategoryScreen(navController: NavController, viewModel: CategoryViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val permission = Manifest.permission.POST_NOTIFICATIONS
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Open notification
            //   Log.d("Permission", "Granted")
        } else {
            // Show dialog
            // Log.d("Permission", "Denied")
            //openPermissionDialog = true
        }
    }
    LaunchedEffect(key1 = true, block = {

        checkAndRequestCameraPermission(context, permission, launcher)
    })

    viewModel.categoryData.observeAsState().value?.let {
        LazyColumn {
            items(it.map { it }, key = { it.id }) { item ->
                CategoryItem(item) {
                    navController.navigate(Routing.ChannelScreen.routeName)
                }
            }
        }
    }


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