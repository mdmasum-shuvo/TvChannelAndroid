package com.appifly.network.remote_data.model.channel

data class ChannelNetwork(
    val category_id: Int,
    val channel_id: Int,
    val channel_image_url: String,
    val channel_live_url: String,
    val channel_name: String,
    val is_popular: Boolean,
)