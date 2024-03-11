@file:OptIn(ExperimentalFoundationApi::class)

package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.appifly.app_data_source.dto.BannerDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.borderColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopBannerItem(dataList: List<BannerDto>) {
    val sliderList = dataList.size
    val configuration = LocalConfiguration.current

    val pagerState = rememberPagerState { sliderList }
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        while (true) {
            delay(5000)

            if (pagerState.currentPage != sliderList - 1) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            } else {
                pagerState.animateScrollToPage(0)
            }

            // pagerState.animateScrollToItem(currentIndex.value)
        }

    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = dataList[page].imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {

                    }).build()
            )
        Column(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp)

        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),

                border = BorderStroke(width = 1.dp, color = borderColor)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            }
            SpacerHeight(12)
            Row {
                RegularChannelItem(
                    modifier = Modifier.size(48.dp),
                    isRegularItem = false,
                    item = ChannelDto(iconUrl = dataList[page].iconUrl)
                )
                SpacerWidth(10)

                Column {
                    TextView14_W500(value = dataList[page].title)
                    TextView12_W400(
                        value = dataList[page].date ?: "N/A",
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }


            }

        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewTopBannerItem() {
    TvChannelTheme {
        //TopBannerItem()
    }
}