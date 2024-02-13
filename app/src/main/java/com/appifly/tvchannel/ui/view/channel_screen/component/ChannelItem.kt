package com.appifly.tvchannel.ui.view.channel_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.app_data_source.dto.ChannelDto

@Composable
fun ChannelItem(item: ChannelDto) {
    val painter =
        rememberImagePainter(data = item.iconUrl,
            builder = {
            })
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )
    }
}