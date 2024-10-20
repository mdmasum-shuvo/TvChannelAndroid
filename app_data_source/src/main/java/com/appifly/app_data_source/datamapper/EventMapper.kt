package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.dto.EventDto
import com.appifly.cachemanager.model.EventChannelJoin
import com.appifly.cachemanager.model.EventEntity
import com.appifly.network.remote_data.model.event.EventNetwork


fun EventNetwork.toEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        channelListId = this.channelListId,
        endTime = this.endTime,
        startTime = this.matchStartTime,
        status = this.status,
        teamOneName = this.teamOneName,
        teamTwoName = this.teamTwoName,
        teamOneImageUrl = this.team_one_image_url,
        teamTwoImageUrl = this.team_two_image_url
    )
}


fun EventChannelJoin.toDto(): EventDto {
    return EventDto(
        channelListId = this.channelListId,
        endTime = this.endTime,
        startTime = this.startTime,
        status = this.status,
        teamOneName = this.teamOneName,
        teamTwoName = this.teamTwoName,
        teamOneImageUrl = this.teamOneImageUrl,
        teamTwoImageUrl = this.teamTwoImageUrl,
        channelIconUrl = iconUrl,
        liveUrl = liveUrl,
        referUrl = refererUrl,
        channelName = name, catId = catId
    )
}

fun EventDto.toChannelDto(): ChannelDto {
    return ChannelDto(
        id = channelListId,
        name = channelName,
        liveUrl = liveUrl,
        liveChannelReferer = referUrl,
        iconUrl = channelIconUrl,
        catId = catId
    )

}
