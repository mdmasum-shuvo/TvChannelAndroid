package com.appifly.tvchannel.ui.view.menu

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.admob.AdmobBannerAdaptive
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.utils.AppUtils


@Composable
fun MenuScreen(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MainTopBar(onSearchIconClick = {navController.navigate(Routing.SearchScreen.routeName) })
        }

        item {
            SpacerHeight(height =  MaterialTheme.dimens.stdDimen16)
        }

        item {
            MenuItem(drawableId = R.drawable.share_icon, context.getString(R.string.share_app), context.getString(R.string.share_app_dec)){
                AppUtils.shareApp(context)
            }
        }
        item {
            MenuItem(drawableId = R.drawable.about_us_icon, context.getString(R.string.about_us),context.getString(R.string.about_us_desc) ){
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Nuveq+Soft")
                ContextCompat.startActivity(context, openURL, null)
            }
        }
        item {
            MenuItem(drawableId = R.drawable.more_icon, context.getString(R.string.more_app),context.getString(R.string.more_app_desc) ){
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Nuveq+Soft")
                ContextCompat.startActivity(context, openURL, null)
            }
        }
        item {
            MenuItem(drawableId = R.drawable.rate_icon, context.getString(R.string.rate_us),context.getString(R.string.rate_us_desc) ){
                AppUtils.rateThisApp(context)

            }
        }
        item {
            AdmobBannerAdaptive()

        }

    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMenuScreen() {
    val context= LocalContext.current
    TvChannelTheme {
        MenuScreen(NavController(context))
    }
}