package com.appifly.tvchannel.routing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.appifly.tvchannel.R

/**
 *
 * @param routeName used in composable fun to pass destination name
 * @Constant variable declare as route name
 * @sample com.appifly.tvchannel.routing
 *
 */


const val CATEGORY_SCREEN = "CATEGORY_SCREEN"
const val CHANNEL_SCREEN = "CHANNEL_SCREEN"
const val HOME_SCREEN = "HOME_SCREEN"
const val FAVORITE_SCREEN = "FAVORITE_SCREEN"
const val MENU_SCREEN = "MENU_SCREEN"

sealed class Routing(
    val routeName: String, val title: String? = "", val drawable: Int = R.drawable.bottom_home_icon
) {
    data object CategoryScreen : Routing(routeName = CATEGORY_SCREEN)
    data object FavoriteScreen : Routing(routeName = FAVORITE_SCREEN,title = "Favorite", drawable =R.drawable.bottom_fav_icon )
    data object MenuScreen : Routing(routeName = MENU_SCREEN,title = "Menu", drawable =R.drawable.bottom_menu_icon)
    data object ChannelScreen : Routing(routeName = CHANNEL_SCREEN,title = "Channel", drawable =R.drawable.bottom_tv_icon)
    data object HomeScreen : Routing(routeName = HOME_SCREEN,title = "Home", drawable =R.drawable.bottom_home_icon)

}