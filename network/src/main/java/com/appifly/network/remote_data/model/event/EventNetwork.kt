package com.appifly.network.remote_data.model.event

data class EventNetwork(
    val channelListId: Int,
    val endTime: String,
    val id: Int,
    val startTime: String,
    val status: Int,
    val teamOneName: String,
    val teamTwoName: String,
    val team_one_image_url: String,
    val team_two_image_url: String
)