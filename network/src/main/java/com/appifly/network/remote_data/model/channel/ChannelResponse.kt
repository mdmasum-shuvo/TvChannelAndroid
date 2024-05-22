package com.appifly.network.remote_data.model.channel

class ChannelResponse (
    val status: Int,
    val message: String,
    val data: List<ChannelNetwork>
)