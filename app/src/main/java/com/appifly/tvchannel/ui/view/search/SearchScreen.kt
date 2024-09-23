package com.appifly.tvchannel.ui.view.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.SearchChannelViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.BasicTextField
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.dimens

@Composable
fun SearchScreen(
    searchChannelViewModel: SearchChannelViewModel,
    channelViewModel: ChannelViewModel,
    navController: NavController
) {
    val value = remember { mutableStateOf("") }

    LaunchedEffect(key1 = value.value, block = {
        searchChannelViewModel.searchChannel(value.value)
    })

    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)

            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clickable {
                            navController.navigateUp()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),

                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            BasicTextField(
                                isKeyboardShown = true,
                                inputValue = value,
                                placeholder = context.getString(R.string.search_place_holder)
                            )
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                searchChannelViewModel.channelData.observeAsState().value?.let {
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
}
