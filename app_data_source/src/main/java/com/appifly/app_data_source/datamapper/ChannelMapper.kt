package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.network.remote_data.model.channel.ChannelNetwork

fun ChannelNetwork.toEntity(): ChannelEntity {

    return ChannelEntity(
        id = channel_id,
        catId = category_id,
        name = channel_name,
        iconUrl =channel_image_url ,
        liveUrl = channel_live_url,
        isPopular = is_popular,

    )
}



fun ChannelEntity.toDto(): ChannelDto {
    return ChannelDto(
        id = id,
        catId = catId,
        name = name,
        iconUrl =iconUrl,
        liveUrl = liveUrl,
    )
}

fun getSubString(channelImageUrl: String): String? {
    val imgUrl:StringBuilder=java.lang.StringBuilder()
    imgUrl.append(channelImageUrl.replace("https://drive.google.com/file/d/","").replace("/view?usp=sharing",""))
    return imgUrl.toString()
}