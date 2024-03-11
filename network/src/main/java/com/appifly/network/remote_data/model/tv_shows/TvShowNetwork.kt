package com.appifly.network.remote_data.model.tv_shows

data class TvShowNetwork(
    val id: Int,
    val title: String,
    val date: String?,
    val image_url: String,
    val channel_id:Int
)