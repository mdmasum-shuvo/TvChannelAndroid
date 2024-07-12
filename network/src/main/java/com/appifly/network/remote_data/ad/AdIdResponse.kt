package com.appifly.network.remote_data.ad

data class AdIdResponse(
    val data: AdIdData?,
    val message: String,
    val status: Int
)