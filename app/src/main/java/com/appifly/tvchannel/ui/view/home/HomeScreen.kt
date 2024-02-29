package com.appifly.tvchannel.ui.view.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.FrequentlyPlayedItem
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.TopBannerItem
import com.appifly.tvchannel.ui.common_component.TvSeriesItem
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.common_component.CategoryListSection
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun HomeScreen(viewModel: CategoryViewModel= hiltViewModel()) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MainTopBar()
        }
        item {
            SpacerHeight(height = 16)

        }
        item {
            TopBannerItem()
        }
        item {
            viewModel.categoryData.observeAsState().value?.let {
                CategoryListSection(it)

            }


        }
        item {
            HeaderText(context.getString(R.string.frequently_played), "See all")
        }

        item {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
            ) {
                items(10) {
                    FrequentlyPlayedItem()
                }
            }
        }



        item {
            HeaderText("Popular Channel", "See all")
        }

        item {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 24.dp)
            ) {
                items(10) {
                    RegularChannelItem()
                }
            }
        }

        item {
            HeaderText(context.getString(R.string.tv_series), "See all")
        }

        item {
            TvSeriesItem()

        }

        item {
            HeaderText("Favorites Channel", "See all")
        }

        item {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
            ) {
                items(10) {
                    FrequentlyPlayedItem()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewHomeSceen() {
    TvChannelTheme {
        HomeScreen()
    }
}