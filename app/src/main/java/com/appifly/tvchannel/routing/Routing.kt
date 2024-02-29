package com.appifly.tvchannel.routing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

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
    val routeName: String, val title: String? = "", val drawable: ImageVector =Icons.Filled.Home
) {
    data object CategoryScreen : Routing(routeName = CATEGORY_SCREEN)
    data object FavoriteScreen : Routing(routeName = FAVORITE_SCREEN,title = "Favorite", drawable =Icons.Outlined.Favorite )
    data object MenuScreen : Routing(routeName = MENU_SCREEN,title = "Menu", drawable =Icons.Outlined.Menu)
    data object ChannelScreen : Routing(routeName = CHANNEL_SCREEN,title = "Channel", drawable =Icons.Outlined.PlayArrow)
    data object HomeScreen : Routing(routeName = HOME_SCREEN)

}