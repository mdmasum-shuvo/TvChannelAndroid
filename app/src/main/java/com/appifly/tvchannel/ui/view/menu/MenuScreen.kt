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
import com.appifly.tvchannel.R
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
            MenuItem(drawableId = R.drawable.theme, "App theme", "Action, Adventure, Thrill")
        }
        item {
            MenuItem(drawableId = R.drawable.share_icon, "Share the App", "Share the app with your friends")
        }
        item {
            MenuItem(drawableId = R.drawable.about_us_icon, "About Us", "Social links, policy")
        }
        item {
            MenuItem(drawableId = R.drawable.more_icon, "More Apps", "Other apps from AppiFly")
        }
        item {
            MenuItem(drawableId = R.drawable.rate_icon, "Rate Us", "Rate our app on google play store")
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