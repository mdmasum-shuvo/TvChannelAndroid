package com.appifly.tvchannel.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
// Set of Material typography styles to start with
val Typography = Typography(


    headlineLarge = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
        letterSpacing = 0.5.sp,
    ),


    headlineMedium = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fonts_ballo_da,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)