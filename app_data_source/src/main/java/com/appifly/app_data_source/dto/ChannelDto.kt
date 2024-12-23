package com.appifly.app_data_source.dto

import androidx.compose.runtime.Immutable

@Immutable
data class ChannelDto(
    var id: Int?=null,
    var catId: Int?=null,
    var name: String?=null,
    var iconUrl: String?=null,
    var liveUrl: String?=null,
    var liveChannelReferer: String?=null,
    var isFavorite: Boolean?=null,
)
