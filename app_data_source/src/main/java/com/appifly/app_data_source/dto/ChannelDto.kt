package com.appifly.app_data_source.dto

data class ChannelDto(
    val id: Int,
    val catId: Int,
    val name: String?,
    val iconUrl: String?,
    val liveUrl: String?
)
