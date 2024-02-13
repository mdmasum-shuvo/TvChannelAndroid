package com.appifly.tvchannel.ui.view.category.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.appifly.app_data_source.dto.CategoryDto

@Composable
fun CategoryItem(item: CategoryDto, onItemClick: () -> Unit) {
    val painter =
        rememberImagePainter(data = item.imageUrl,
            builder = {


            })
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable { onItemClick() }) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(300.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}