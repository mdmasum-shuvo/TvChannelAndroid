package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.borderColor

@Composable
fun FrequentlyPlayedItem() {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://png.pngtree.com/png-clipart/20201202/ourmid/pngtree-breaking-news-lower-third-with-tv-logo-png-image_2504689.jpg")
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
                .height(90.dp)
                .width(130.dp),
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
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageComponent(R.drawable.channel)

            }

        }
        TextView12_W500(value = "T Sports", color = MaterialTheme.colorScheme.onTertiary)

    }

}

@Composable
fun RegularChannelItem(modifier: Modifier=Modifier.size(80.dp), borderC: Color =borderColor,cardColor: Color =MaterialTheme.colorScheme.secondaryContainer) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://png.pngtree.com/png-clipart/20201202/ourmid/pngtree-breaking-news-lower-third-with-tv-logo-png-image_2504689.jpg")
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
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageComponent(R.drawable.hbo_icon)

        }

    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewFrequentlyPlayedItem() {
    TvChannelTheme {
        FrequentlyPlayedItem()
    }
}