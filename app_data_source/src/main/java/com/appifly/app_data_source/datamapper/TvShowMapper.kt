package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.BannerDto
import com.appifly.app_data_source.dto.TvShowDto
import com.appifly.cachemanager.model.BannerChannelJoin
import com.appifly.cachemanager.model.TvShowChannelJoin
import com.appifly.cachemanager.model.TvShowEntity
import com.appifly.network.BuildConfig
import com.appifly.network.remote_data.model.tv_shows.TvShowNetwork

fun TvShowNetwork.toEntity(): TvShowEntity {

    return TvShowEntity(
        id = id,
        title = title,
        date = date,
        imageUrl = getSubString(image_url),
        channelId = channel_id
    )
}


fun TvShowChannelJoin.toDto(): TvShowDto {
    return TvShowDto(
        id = 0,
        title = title,
        date = date,
        imageUrl = BuildConfig.ICON_BASE_URL_DRIVE + imageUrl,
        channelId = channelId,
        iconUrl = BuildConfig.ICON_BASE_URL_DRIVE + iconUrl
    )
}