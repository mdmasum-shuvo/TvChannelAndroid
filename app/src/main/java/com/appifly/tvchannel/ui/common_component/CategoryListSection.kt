package com.appifly.tvchannel.ui.common_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.tvchannel.ui.theme.dimens

@Composable
fun CategoryListSection(list: List<CategoryDto>,selectedIndex:MutableState<Int> ,onItemClick: (CategoryDto) -> Unit = { }) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(start = 16.dp, top = MaterialTheme.dimens.stdDimen24, bottom = MaterialTheme.dimens.stdDimen24)
    ) {
        itemsIndexed(list) {index, item ->
            CategoryItem(item,selectedIndex,index) {
                onItemClick(item)
            }
        }
    }
}