package com.appifly.tvchannel.ui.view.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.admob.AdmobBannerAdaptive
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.HomeScreen


@Composable
fun MenuScreen() {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MainTopBar()
        }

        item {
            SpacerHeight(height =  MaterialTheme.dimens.stdDimen16)
        }
        item {
            MenuItem(drawableId = R.drawable.theme, context.getString(R.string.app_theme),  context.getString(R.string.dark_or_light))
        }
        item {
            MenuItem(drawableId = R.drawable.share_icon, context.getString(R.string.share_app), context.getString(R.string.share_app_dec))
        }
        item {
            MenuItem(drawableId = R.drawable.about_us_icon, context.getString(R.string.about_us),context.getString(R.string.about_us_desc) )
        }
        item {
            MenuItem(drawableId = R.drawable.more_icon, context.getString(R.string.more_app),context.getString(R.string.more_app_desc) )
        }
        item {
            MenuItem(drawableId = R.drawable.rate_icon, context.getString(R.string.rate_us),context.getString(R.string.rate_us_desc) )
        }
        item {
            AdmobBannerAdaptive()

        }

    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMenuScreen() {
    TvChannelTheme {
        MenuScreen()
    }
}