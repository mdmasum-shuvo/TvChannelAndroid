package com.appifly.network.remote_data.model.channel

data class ChannelNetwork(
    val id: Int,
    val categoryId: Int,
    val image_url: String,
    val liveChannelUrl: String,
    val liveChannelReferer: String?,
    val channelName: String,
    val is_popular: Int,
)