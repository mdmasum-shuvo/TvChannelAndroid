package com.appifly.tvchannel.ui.common_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appifly.app_data_source.dto.CategoryDto

@Composable
fun CategoryListSection(list: List<CategoryDto>,selectedIndex:MutableState<Int> ,onItemClick: (CategoryDto) -> Unit = { }) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp)
    ) {
        itemsIndexed(list) {index, item ->
            CategoryItem(item,selectedIndex,index) {
                onItemClick(item)

            }
        }
    }
}