package com.appifly.network.remote_data

object HttpParam {
    const val SHEET_API_END_POINT = "exec?" // sheet api end point

    // Replace by your sheet name if you changed any
    const val SHEET_NAME_CATEGORY = "category"
    const val SHEET_NAME_CHANNEL_LIST = "channel_list"
    const val SHEET_NAME_BANNER = "banner"
    const val SHEET_NAME_TV_SHOWS = "tv_shows"

    const val SUCCESS_STATUS_CODE="200"
    const val ERROR_STATUS_CODE="404"
    const val SERVER_NOT_FOUND_EXCEPTION="Couldn't reach server. Check your internet connection."
    const val SUCCESSFUL_TEXT="Successfully Load Data"
}