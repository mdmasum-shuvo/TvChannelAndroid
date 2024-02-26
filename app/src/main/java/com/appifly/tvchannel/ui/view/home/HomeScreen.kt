package com.appifly.tvchannel.ui.view.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.CategoryItem
import com.appifly.tvchannel.ui.common_component.FrequentlyPlayedItem
import com.appifly.tvchannel.ui.common_component.ImageComponent
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MainTopBar()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

        }
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
            ) {
                ImageComponent(R.drawable.banner, Modifier.height(200.dp), contentScale = ContentScale.Crop)
            }
        }
        item {
/*
            viewModel.categoryData.observeAsState().value?.let {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                ) {
                    items(it.map { it }, key = { it.id }) { item ->
                        CategoryItem(item) {

                        }
                    }

                }
            }*/

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 32.dp)
            ) {
                items(10) {
                    CategoryItem(CategoryDto(1, "Drama", "")) {

                    }
                }
            }

        }
        item {
            HeaderText("Frequently Played", "See all")
        }

        item {

            LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(start = 16.dp, top = 10.dp)
        ) {
            items(10) {
                FrequentlyPlayedItem()
            }
        } }
    }
}

@PreviewScreenSizes
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewHomeSceen() {
    TvChannelTheme {
        HomeScreen()
    }
}