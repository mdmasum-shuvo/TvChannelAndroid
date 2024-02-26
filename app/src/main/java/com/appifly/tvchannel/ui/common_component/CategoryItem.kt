package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme

@Composable
fun CategoryItem() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                ImageComponent(
                    drawableId = R.drawable.mdi_comedy,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        TextView10_W400(value = "Drama", color = MaterialTheme.colorScheme.onTertiary)

    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCategoryItem() {
    TvChannelTheme {
        CategoryItem()
    }
}