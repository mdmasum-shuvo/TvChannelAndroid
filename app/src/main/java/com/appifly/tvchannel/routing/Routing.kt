package com.appifly.tvchannel.routing

import com.appifly.tvchannel.R

/**
 *
 * @param routeName used in composable fun to pass destination name
 * @Constant variable declare as route name
 * @sample com.appifly.tvchannel.routing
 *
 */


const val CHANNEL_SCREEN = "CHANNEL_SCREEN"
const val HOME_SCREEN = "HOME_SCREEN"
const val FAVORITE_SCREEN = "FAVORITE_SCREEN"
const val MENU_SCREEN = "MENU_SCREEN"
const val FAVORITE_LIST_SCREEN = "FAVORITE_LIST_SCREEN"
const val CHANNEL_DETAIL_SCREEN = "CHANNEL_DETAIL_SCREEN"
const val SEE_ALL_CHANNEL_SCREEN = "SEE_ALL_CHANNEL_SCREEN"
const val SEARCH_SCREEN = "SEARCH_SCREEN"

sealed class Routing(
    val routeName: String, val title: String? = "", val drawable: Int = R.drawable.bottom_home_icon
) {
    data object FavoriteChannelListScreen : Routing(routeName = FAVORITE_LIST_SCREEN)
    data object ChannelDetailScreen : Routing(routeName = CHANNEL_DETAIL_SCREEN)
    data object FavoriteScreen : Routing(routeName = FAVORITE_SCREEN,title = "Favorite", drawable =R.drawable.bottom_fav_icon )
    data object MenuScreen : Routing(routeName = MENU_SCREEN,title = "Menu", drawable =R.drawable.bottom_menu_icon)
    data object ChannelScreen : Routing(routeName = CHANNEL_SCREEN,title = "Event", drawable =R.drawable.bottom_tv_icon)
    data object HomeScreen : Routing(routeName = HOME_SCREEN,title = "Live", drawable =R.drawable.bottom_home_icon)
    data object SeeAllChannelScreen : Routing(routeName = SEE_ALL_CHANNEL_SCREEN,)
    data object SearchScreen : Routing(routeName = SEARCH_SCREEN)

}