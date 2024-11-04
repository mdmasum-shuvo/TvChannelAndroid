package com.appifly.tvchannel.tv_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import com.appifly.tvchannel.ui.common_component.TextView32W500
import com.appifly.tvchannel.ui.theme.CategoryColor
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
