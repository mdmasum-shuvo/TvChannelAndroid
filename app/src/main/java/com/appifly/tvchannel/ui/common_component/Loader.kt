package com.appifly.tvchannel.ui.common_component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.ui.theme.gradientColor1


@Composable
fun Loader(){
    CircularProgressIndicator(
        color =gradientColor1,
        strokeWidth = 2.dp)
}