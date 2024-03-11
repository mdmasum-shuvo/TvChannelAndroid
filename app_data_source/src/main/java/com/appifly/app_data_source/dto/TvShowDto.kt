package com.appifly.app_data_source.dto

data class TvShowDto(
    val id: Int,
    val title: String,
    val date: String?,
    val imageUrl: String,
    val channelId: Int,
    val iconUrl: String
)
