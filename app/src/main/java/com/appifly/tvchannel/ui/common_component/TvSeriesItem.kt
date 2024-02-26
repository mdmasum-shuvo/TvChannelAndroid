package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.borderColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvSeriesItem() {
   val sliderList=5
    val configuration = LocalConfiguration.current

    val pagerState = rememberPagerState{sliderList}
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
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://png.pngtree.com/png-clipart/20201202/ourmid/pngtree-breaking-news-lower-third-with-tv-logo-png-image_2504689.jpg")
                .apply(block = fun ImageRequest.Builder.() {

                }).build()
        )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp,bottom=32.dp)

        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = borderColor)
            ) {
/*
            Image(
                painter = Painter,
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )*/
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageComponent(R.drawable.series, contentScale = ContentScale.Crop)

                }

            }
            SpacerHeight(12)
            Row {
                RegularChannelItem(modifier = Modifier.size(48.dp))
                SpacerWidth(10)

                Column {
                    TextView16_W500(value = "Paper Girls | English | Season 1")
                    TextView12_W400(
                        value = "Every Sunday, 8:30 PM",
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }


            }

        }
    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewTvSeriesItem() {
    TvChannelTheme {
        TvSeriesItem()
    }
}