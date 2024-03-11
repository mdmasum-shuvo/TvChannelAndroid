package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.borderColor
import com.appifly.tvchannel.ui.theme.gradientColor1
import com.appifly.tvchannel.ui.theme.gradientColor2

@Composable
fun FrequentlyPlayedItem(
    item: ChannelDto,
    onItemClick: (ChannelDto) -> Unit = { },
    onFavClick: (Int, Boolean) -> Unit
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = item.iconUrl)
                .apply(block = fun ImageRequest.Builder.() {

                }).build()
        )
    Column(

    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .clickable { onItemClick(item) }
                .height(90.dp)
                .width(130.dp),
            border = BorderStroke(width = 1.dp, color = borderColor)
        ) {

            Box {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.iconUrl)
                            .build(),
                        contentScale = ContentScale.Fit,
                        contentDescription = "ImageRequest example",
                    )


                }

                Box(modifier = Modifier
                    .clickable {
                        onFavClick(item.id!!, item.isFavorite!!)
                    }
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)) {
                    GradientFavIcon(item.isFavorite)
                }
            }

        }
        TextView14_W500(value = item.name ?: "N/A", color = MaterialTheme.colorScheme.onTertiary)

    }

}

@Composable
fun RegularChannelItem(
    item: ChannelDto? = null,
    isRegularItem: Boolean = true,
    modifier: Modifier = Modifier.size(80.dp),
    borderC: Color = borderColor,
    cardColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = item?.iconUrl)
                .apply(block = fun ImageRequest.Builder.() {

                }).build()
        )
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = borderC)
    ) {

        Box {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item?.iconUrl)
                        .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "ImageRequest example",
                )
            }
            if (isRegularItem) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    GradientFavIcon(item?.isFavorite)
                }
            }

        }

    }

}

@Composable
fun GradientFavIcon(isFavorite: Boolean?) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            gradientColor1,
            gradientColor2,
        ),
        start = Offset(12f, 12f),
        end = Offset(12f, 52f),
    )
    Icon(
        modifier = Modifier
            .size(16.dp)
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.SrcAtop)
                }
            },
        imageVector = if (isFavorite == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = null,
    )
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewFrequentlyPlayedItem() {
    TvChannelTheme {
        //FrequentlyPlayedItem(ChannelDto(1, 1, "", "", ""))

        //GradientFavIcon()

    }
}