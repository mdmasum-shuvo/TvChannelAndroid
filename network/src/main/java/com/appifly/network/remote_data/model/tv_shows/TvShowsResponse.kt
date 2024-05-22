package com.appifly.network.remote_data.model.tv_shows

class TvShowsResponse (
    val status: Int,
    val message: String,
    val data: List<TvShowNetwork>
)