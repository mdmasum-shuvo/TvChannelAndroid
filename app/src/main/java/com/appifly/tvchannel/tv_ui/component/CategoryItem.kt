package com.appifly.tvchannel.tv_ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.ui.common_component.TextView32W500
import com.appifly.tvchannel.ui.theme.CategoryColor
import com.appifly.tvchannel.ui.theme.PreviewerTheme
import com.appifly.tvchannel.ui.theme.darkBackground

@Composable
fun TvCategoryItem(
    title: String,
    index: Int,
    selectedIndex: MutableState<Int>,
    focusRequester: FocusRequester,
    onItemClick: (String) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,

        colors = CardDefaults.cardColors(
            containerColor =   if (selectedIndex.value == index) CategoryColor else darkBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .selectable(selected = selectedIndex.value == index, onClick = {
                if (selectedIndex.value != index) {
                    selectedIndex.value = if (selectedIndex.value != index) index else -1
                    onItemClick(title)
                }

            })

            .height(72.dp),
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
        Surface(color = darkBackground) {
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