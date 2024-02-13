package com.appifly.tvchannel.ui.view.category

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.view.category.component.CategoryItem


@Composable
fun CategoryScreen(navController: NavController, viewModel: CategoryViewModel = hiltViewModel()) {
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