package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.BannerDto
import com.appifly.cachemanager.model.BannerEntity
import com.appifly.cachemanager.model.BannerChannelJoin
import com.appifly.network.BuildConfig
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


/*fun BannerEntity.toDto(): BannerDto {
    return BannerDto(
        id = id,
        title = title,
        date = date,
        imageUrl = BuildConfig.ICON_BASE_URL_DRIVE + imageUrl,
        channelId = channelId


    )
}*/


fun BannerChannelJoin.toDto(): BannerDto {
    return BannerDto(
        id = 0,
        title = title,
        date = date,
        imageUrl = BuildConfig.ICON_BASE_URL_DRIVE + imageUrl,
        channelId = channelId,
        iconUrl=BuildConfig.ICON_BASE_URL_DRIVE+iconUrl


    )
}