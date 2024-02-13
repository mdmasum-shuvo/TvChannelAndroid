package com.appifly.tvchannel.ui.view.category

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel


@Composable
fun CategoryScreen(viewModel: CategoryViewModel = hiltViewModel()) {
    viewModel.categoryData.observeAsState().value?.let {
        LazyColumn {
            items(it.map { it }, key = { it.id }) { item ->

                Text(text = item.name ?: "")
            }
        }
    }


}