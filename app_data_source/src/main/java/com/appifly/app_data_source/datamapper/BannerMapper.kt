package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.BannerDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.model.BannerChannelJoin
import com.appifly.cachemanager.model.BannerEntity
import com.appifly.network.remote_data.banner.BannerNetwork

fun BannerNetwork.toEntity(): BannerEntity {

    return BannerEntity(
        id = id,
        title = title,
        date = date,
        imageUrl = getSubString(image_url),
        channelId = channel_id
    )
}


fun BannerChannelJoin.toDto(): BannerDto {
    return BannerDto(
        id = 0,
        title = title,
        date = date,
        imageUrl =  imageUrl+"",
        channelId = channelId,
        iconUrl = iconUrl,
        liveUrl = liveUrl,
        channelName = name,
        catId = catId

    )
}

fun BannerDto.toDto(): ChannelDto {
    return ChannelDto(
        id = channelId,
        iconUrl = iconUrl,
        liveUrl = liveUrl,
        name = channelName,
        catId = catId
    )
}