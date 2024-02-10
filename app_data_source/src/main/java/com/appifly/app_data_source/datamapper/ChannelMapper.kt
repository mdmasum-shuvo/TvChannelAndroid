package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.network.remote_data.model.channel.ChannelNetwork

fun ChannelNetwork.toEntity(): ChannelEntity {

    return ChannelEntity(
        id = channel_id,
        catId = category_id,
        name = channel_name,
        iconUrl = channel_image_url,
        liveUrl = channel_live_url
    )
}

fun ChannelEntity.toDto(): ChannelDto {
    return ChannelDto(
        id = id,
        catId = catId,
        name = name,
        iconUrl = iconUrl,
        liveUrl = liveUrl
    )
}