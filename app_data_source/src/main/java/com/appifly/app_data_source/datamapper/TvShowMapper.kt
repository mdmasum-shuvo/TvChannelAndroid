package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.dto.TvShowDto
import com.appifly.cachemanager.model.TvShowChannelJoin
import com.appifly.cachemanager.model.TvShowEntity
import com.appifly.network.remote_data.model.tv_shows.TvShowNetwork

fun TvShowNetwork.toEntity(): TvShowEntity {

    return TvShowEntity(
        id = id,
        title = showName,
        date = "${showDate} , ${showTime}",
        imageUrl = image_url,
        channelId = channelListId
    )
}


fun TvShowChannelJoin.toDto(): TvShowDto {
    return TvShowDto(
        id = 0,
        title = title,
        date = date,
        imageUrl = imageUrl,
        channelId = channelId,
        iconUrl = iconUrl,
        liveUrl = liveUrl,
        channelName = name,
        catId = catId
    )
}

fun TvShowDto.toDto(): ChannelDto {
    return ChannelDto(
        id = channelId,
        iconUrl = iconUrl,
        liveUrl = liveUrl,
        name = channelName,
        catId = catId
    )
}