package com.appifly.app_data_source.dto

data class EventDto(
    val channelListId: Int?,
    val endTime: String?,
    val id: Int?,
    val startTime: String?,
    val status: Int?,
    val teamOneName: String?,
    val teamTwoName: String?,
    val teamOneImageUrl: String?,
    val teamTwoImageUrl: String?
)

data class EventJoin(
    val channelListId: Int?,
    val endTime: String?,
    val id: Int?,
    val startTime: String?,
    val status: Int?,
    val teamOneName: String?,
    val teamTwoName: String?,
    val teamOneImageUrl: String?,
    val teamTwoImageUrl: String?
)
