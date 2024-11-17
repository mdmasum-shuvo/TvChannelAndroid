package com.appifly.network.remote_data.ad_config

data class AdConfigResponse(
    val ad_networks: List<AdNetwork>,
    val message: String,
    val status: Int
)