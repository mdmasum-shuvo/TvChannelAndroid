package com.appifly.network.remote_data.model.tv_shows

data class TvShowNetwork(
    val id: Int,
    val showName: String,
    val showDate: String?,
    val showTime: String?,
    val image_url: String,
    val channelListId:Int
)