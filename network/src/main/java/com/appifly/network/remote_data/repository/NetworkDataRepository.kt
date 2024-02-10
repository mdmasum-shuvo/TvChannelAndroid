package com.appifly.network.remote_data.repository

interface NetworkDataRepository {

    suspend fun getAllCategory()
    suspend fun getAllChannel()
}