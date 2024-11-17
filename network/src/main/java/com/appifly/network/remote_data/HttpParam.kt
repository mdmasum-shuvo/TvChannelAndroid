package com.appifly.network.remote_data

object HttpParam {
    const val SHEET_API_END_POINT = "exec?" // sheet api end point

    // Replace by your sheet name if you changed any
    const val CATEGORY = "category-list"
    const val CHANNEL_LIST = "channel-list"
    const val BANNER = "banner-list"
    const val TV_SHOWS = "tvshow-list"
    const val ADVERTISEMENT = "ads/config"

    const val SUCCESS_STATUS_CODE="200"
    const val ERROR_STATUS_CODE="404"
    const val SERVER_NOT_FOUND_EXCEPTION="Couldn't reach server. Check your internet connection."
    const val SUCCESSFUL_TEXT="Successfully Load Data"
}