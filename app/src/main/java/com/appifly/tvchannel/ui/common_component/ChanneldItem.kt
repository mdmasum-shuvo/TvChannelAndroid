package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.cardBackgroundColorDark
import com.appifly.tvchannel.utils.shimmerEffect

@Composable
fun LargeChannelItem(
    item: ChannelDto,
    onItemClick: (ChannelDto) -> Unit = { },
) {
    val showShimmer = remember { mutableStateOf(true) }

    val context = LocalContext.current
    Card(
        onClick = {onItemClick(item)},
      //  shape = CardDefaults.shape(shape = MaterialTheme.shapes.medium),
        colors =
        CardDefaults.colors(
            containerColor = cardBackgroundColorDark,
            focusedContainerColor = cardBackgroundColorDark
        ),
        border =
        CardDefaults.border(
            focusedBorder =
            Border(
                border = BorderStroke(width = 2.dp, color = cardBackgroundColorDark),

            ),
        ),

        modifier = Modifier
            .height(150.dp)
    ) {
        Column(

        ) {
            Card (
                onClick = {},
              //  shape = CardDefaults.shape(shape = MaterialTheme.shapes.large),
                modifier = Modifier.background(
                    shimmerEffect(
                        targetValue = 1300f,
                        showShimmer = showShimmer.value
                    )
                ).height(120.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.iconUrl)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = context.getString(R.string.load_network_image),
                        onSuccess = {
                            showShimmer.value = false
                        }, onError = {
                            showShimmer.value = false
                        }
                    )


                }

            }
            SpacerHeight(4.dp)

            TextView14W500(value = item.name ?: "N/A", color = MaterialTheme.colorScheme.onTertiary, modifier = Modifier.padding(start = 4.dp))

        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewFrequentlyPlayedItem() {
    TvChannelTheme {

        //GradientFavIcon()

    }
}