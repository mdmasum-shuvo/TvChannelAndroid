package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.appifly.app_data_source.datamapper.toChannelDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.dto.EventDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.lightBackground
import com.appifly.tvchannel.utils.shimmerEffect


@Composable
fun LiveEventCardItem(eventDto: EventDto, onItemClick: (ChannelDto) -> Unit = { }) {
    val context = LocalContext.current
    val showShimmer = remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), onClick = {
            onItemClick(eventDto.toChannelDto())
        }

    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(), Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .background(
                                shimmerEffect(
                                    targetValue = 1300f,
                                    showShimmer = showShimmer.value
                                )
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(40.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(eventDto.teamOneImageUrl).diskCachePolicy(CachePolicy.ENABLED)
                                .build(),

                            contentScale = ContentScale.Fit,
                            contentDescription = context.getString(R.string.load_network_image),
                            onSuccess = {
                                showShimmer.value = false
                            }, onError = {
                                showShimmer.value = false

                            }
                        )
                    }

                    SpacerHeight(4.dp)
                    TextView12W400(value = eventDto.teamOneName ?: "", color = lightBackground)
                }
                TextView18W500(value = "VS", color = lightBackground)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .background(
                                shimmerEffect(
                                    targetValue = 1300f,
                                    showShimmer = showShimmer.value
                                )
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(40.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(eventDto.teamTwoImageUrl).diskCachePolicy(CachePolicy.ENABLED)
                                .build(),

                            contentScale = ContentScale.Fit,
                            contentDescription = context.getString(R.string.load_network_image),
                            onSuccess = {
                                showShimmer.value = false
                            }, onError = {
                                showShimmer.value = false

                            }
                        )
                    }
                    SpacerHeight(4.dp)

                    TextView12W400(value = eventDto.teamTwoName ?: "")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(), Arrangement.SpaceBetween
            ) {
                TextView10W400(value = "Star Sports HD")
                TextView10W400(value = eventDto.startTime ?: "")

            }
        }
    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewLiveEventCard() {
    //LiveEventCardItem()
}