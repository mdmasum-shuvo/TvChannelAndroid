package com.appifly.network.remote_data.repository

import DefaultResponse

interface NetworkDataRepository {

    suspend fun getAllCategory():DefaultResponse
    suspend fun getAllChannel():DefaultResponse
}