package com.appifly.tvchannel.routing

import com.appifly.tvchannel.R

/**
 *
 * @param routeName used in composable fun to pass destination name
 * @Constant variable declare as route name
 * @sample com.appifly.tvchannel.routing
 *
 */


const val HOME_SCREEN = "HOME_SCREEN"
const val CHANNEL_DETAIL_SCREEN = "CHANNEL_DETAIL_SCREEN"

sealed class Routing(
    val routeName: String, val title: String? = "", val drawable: Int = R.drawable.bottom_home_icon
) {
    data object ChannelDetailScreen : Routing(routeName = CHANNEL_DETAIL_SCREEN)
    data object HomeScreen :
        Routing(routeName = HOME_SCREEN, title = "Home", drawable = R.drawable.bottom_home_icon)

}
