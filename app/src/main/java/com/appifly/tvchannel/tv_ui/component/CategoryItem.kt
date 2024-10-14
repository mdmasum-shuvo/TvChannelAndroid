package com.appifly.tvchannel.tv_ui.component


import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import com.appifly.tvchannel.ui.common_component.TextView32W500
import com.appifly.tvchannel.ui.theme.CategoryColor
import com.appifly.tvchannel.ui.theme.PreviewerTheme
import com.appifly.tvchannel.ui.theme.cardBackgroundColorDark
import com.appifly.tvchannel.ui.theme.darkBackground

@Composable
fun TvCategoryItem(
    title: String,
    onItemClick: (String) -> Unit
) {
    Card(
        onClick = {
            onItemClick(title)
        },
      //  shape = CardDefaults.shape(shape = MaterialTheme.shapes.medium),
        border =
        CardDefaults.border(
            focusedBorder =
            Border(
                border = BorderStroke(width = 3.dp, color = cardBackgroundColorDark),

            ),
        ),
        colors =
        CardDefaults.colors(containerColor = darkBackground, focusedContainerColor = CategoryColor),
        modifier = Modifier
            .height(60.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            TextView32W500(value = title)
        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.TV_1080p)
@Composable
fun PreviewTvCategoryItem() {
    val selectedIndex = remember { mutableIntStateOf(0) }

    PreviewerTheme {
        Surface(colors = SurfaceDefaults.colors(containerColor = darkBackground)) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    items(10) { index ->
                        // TvCategoryItem("Bangla", index, selectedIndex)
                    }

                }
            }
        }
    }
}