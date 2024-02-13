package com.appifly.tvchannel.ui.view.channel_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.ui.view.channel_screen.component.ChannelItem

@Composable
fun ChannelScreen(navController: NavController,viewModel: ChannelViewModel= hiltViewModel()) {
    viewModel.channelData?.observeAsState()?.value?.let {
        LazyColumn {
            items(it.map { it }, key = { it.id }) { item ->
                ChannelItem(item)
            }
        }
    }

}