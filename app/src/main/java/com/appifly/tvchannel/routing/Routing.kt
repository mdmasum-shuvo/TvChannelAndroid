package com.appifly.tvchannel.routing

/**
 *
 * @param routeName used in composable fun to pass destination name
 * @Constant variable declare as route name
 * @sample com.appifly.tvchannel.routing
 *
 */


const val CATEGORY_SCREEN = "CATEGORY_SCREEN"
const val CHANNEL_SCREEN = "CHANNEL_SCREEN"

sealed class Routing(
    val routeName: String
) {
    data object CategoryScreen : Routing(routeName = CATEGORY_SCREEN)
    data object ChannelScreen : Routing( routeName = CHANNEL_SCREEN)

}