package com.appifly.tvchannel.ui.common_component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun TextView32W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start

) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
    )
}

@Composable
fun TextView14W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLine: Int = 1
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLine
    )
}
