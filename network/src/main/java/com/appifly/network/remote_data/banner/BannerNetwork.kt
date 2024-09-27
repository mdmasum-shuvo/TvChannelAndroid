package com.appifly.network.remote_data.banner

data class BannerNetwork(
    val id: Int,
    val bannerTitle: String,
    val startTime: String?,
    val description: String?,
    val endTime: String?,
    val image_url: String?,
    val channelListId:Int
)