package com.appifly.app_data_source.dto

import androidx.compose.runtime.Stable

@Stable
data class BannerDto(
    val id: Int,
    val title: String,
    val date: String?,
    val imageUrl: String,
    val channelId: Int,
    val iconUrl: String
)
