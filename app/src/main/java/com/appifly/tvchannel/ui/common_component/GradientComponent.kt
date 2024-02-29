package com.appifly.tvchannel.ui.common_component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.appifly.tvchannel.ui.theme.gradientColor1
import com.appifly.tvchannel.ui.theme.gradientColor2

@Composable
fun gradientColor(): Brush {
    return Brush.verticalGradient(
        colors = listOf(gradientColor1, gradientColor2)
    )
}