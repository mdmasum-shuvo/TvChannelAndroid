package com.appifly.network.remote_data.banner

data class BannerNetwork(
    val id: Int,
    val title: String,
    val date: String?,
    val image_url: String,
    val channel_id:Int
)