package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.network.remote_data.model.channel.ChannelNetwork

fun ChannelNetwork.toEntity(): ChannelEntity {

    return ChannelEntity(
        id = id,
        catId = categoryId,
        name = channelName,
        iconUrl = image_url,
        liveUrl = liveChannelUrl,
        liveChannelReferer = liveChannelReferer,
        isPopular = is_popular==1,

    )
}



fun ChannelEntity.toDto(): ChannelDto {
    return ChannelDto(
        id = id,
        catId = catId,
        name = name,
        iconUrl = iconUrl,
        liveChannelReferer = liveChannelReferer,
        liveUrl = liveUrl,
    )
}
