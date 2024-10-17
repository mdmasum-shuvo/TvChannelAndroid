package com.appifly.network.remote_data.model.event

data class EventResponse(
    val `data`: List<EventNetwork>,
    val message: String,
    val status: Int
)