package com.appifly.tvchannel.ui.common_component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.appifly.tvchannel.ui.theme.fonts_ballo_da

@Composable
fun TextView18_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start

) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = fonts_ballo_da,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        ),
        textAlign = textAlign,
    )
}

@Composable
fun TextView16_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
    )
}

@Composable
fun TextView14_W400(
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
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLine
    )
}

@Composable
fun TextView14_W400_Gradient(
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
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500, brush = gradientColor()
        ),
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLine
    )
}


@Composable
fun TextView14_W500(
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

@Composable
fun TextView12_W400(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Visible,
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        overflow = overflow,

    )
}

@Composable
fun TextView10_W400(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
        textAlign = textAlign,
    )
}
