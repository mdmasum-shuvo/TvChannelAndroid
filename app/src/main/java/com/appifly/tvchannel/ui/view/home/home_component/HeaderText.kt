package com.appifly.tvchannel.ui.view.home.home_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.ui.common_component.TextView14W400Gradient
import com.appifly.tvchannel.ui.common_component.TextView18W500

@Composable
fun HeaderText(title: String?, subTitle: String?="") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        TextView18W500(value = title ?: "")
        TextView14W400Gradient(value = subTitle ?: "")
    }
}