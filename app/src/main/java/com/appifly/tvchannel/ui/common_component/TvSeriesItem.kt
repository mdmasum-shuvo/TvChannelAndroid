package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.dto.TvShowDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.borderColor
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.utils.shimmerEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvSeriesItem(dataList: List<TvShowDto>, onItemClick: (ChannelDto) -> Unit = { }) {
    val sliderList = dataList.size
    val showShimmer = remember { mutableStateOf(true) }
val context= LocalContext.current
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

        }

    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp).clickable {
                onItemClick(dataList[page].toDto())
            }
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .height(MaterialTheme.dimens.tvShowHeight)
                    .fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = borderColor)
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize().background(
                            shimmerEffect(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(dataList[page].imageUrl).diskCachePolicy(CachePolicy.ENABLED)

                            .build(),

                        contentScale = ContentScale.Crop,
                        contentDescription =context.getString(R.string.load_network_image) ,
                        onSuccess = {
                            showShimmer.value = false
                        }, onError = {
                            showShimmer.value = false
                        }
                    )
                }

            }
            SpacerHeight( MaterialTheme.dimens.stdDimen12)
            Row {
                RegularChannelItem(
                    modifier = Modifier.size(MaterialTheme.dimens.channelExtraSmall),
                    item = ChannelDto(iconUrl = dataList[page].iconUrl)
                )
                SpacerWidth( MaterialTheme.dimens.stdDimen10)

                Column {
                    TextView16W500(value = dataList[page].title)
                    TextView12W400(
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
fun PreviewTvSeriesItem() {
    TvChannelTheme {
        // TvSeriesItem()
    }
}