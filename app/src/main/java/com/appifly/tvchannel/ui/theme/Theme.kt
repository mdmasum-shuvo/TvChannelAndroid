package com.appifly.tvchannel.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.appifly.tvchannel.MainActivity

private val DarkColorScheme = darkColorScheme(
    surface = darkBackground,
    primary = darkBackground,
    background = darkBackground,
    secondary = lightBackground,
    tertiary = darkThemeTextColor,
    onTertiary = darkThemeTextColor,
    secondaryContainer = cardBackgroundColorDark
)

private val LightColorScheme = lightColorScheme(

    surface = darkBackground,
    primary = darkBackground,
    background = darkBackground,
    secondary = lightBackground,
    tertiary = darkThemeTextColor,
    onTertiary = darkThemeTextColor,
    secondaryContainer = cardBackgroundColorDark

)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TvChannelTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    activity: Activity = LocalContext.current as MainActivity,

    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    val window = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current


    var typography = CompactTypography
    var appDimens = CompactDimens

    when (window.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {

                appDimens = CompactSmallDimens
                typography = CompactSmallTypography
            } else if (config.screenWidthDp < 599) {

                appDimens = CompactMediumDimens
                typography = CompactMediumTypography
            } else {
                appDimens = CompactDimens
                typography = CompactTypography
            }
        }

        WindowWidthSizeClass.Medium -> {
            appDimens = MediumDimens
            typography = CompactTypography
        }

        WindowWidthSizeClass.Expanded -> {
            appDimens = ExpandedDimens
            typography = ExpandedTypography
        }

    }

    ProvideAppUtils(appDimens = appDimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimens
    @Composable
    get() = LocalAppDimens.current

@Composable
fun PreviewerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val Typography = CompactTypography

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}