package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun ChannelScreen(viewModel: CategoryViewModel= hiltViewModel()) {
    /*    viewModel.channelData?.observeAsState()?.value?.let {
            LazyColumn {
                items(it.map { it }, key = { it.id }) { item ->
                    ChannelItem(item)
                }
            }
        }*/
    /*   LazyVerticalStaggeredGrid(
           columns = StaggeredGridCells.Fixed(3),
           horizontalArrangement = Arrangement.spacedBy(12.dp),
           contentPadding = PaddingValues(
               start = 12.dp,
               top = 16.dp,
               end = 12.dp,
               bottom = 16.dp
           )
       ) {
           items(10) {
               RegularChannelItem(modifier = Modifier.height(100.dp))
           }

       }*/
    val state = rememberScrollState()
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MainTopBar()
        }

        item {
            viewModel.categoryData.observeAsState().value?.let {
                CategoryListSection(it)

            }
        }


        item {
            HeaderText("News Channel")
        }

        item {
            LazyVerticalGrid(
                modifier = Modifier.height(((112 * 10) / 2).dp),
                columns = GridCells.Fixed(3),
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
                items(10) {
                    RegularChannelItem(modifier = Modifier.height(100.dp))
                }

            }
        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelScreen() {
    TvChannelTheme {
        ChannelScreen()
    }
}