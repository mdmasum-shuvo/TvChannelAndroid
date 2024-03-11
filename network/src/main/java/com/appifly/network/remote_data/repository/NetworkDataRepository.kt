package com.appifly.network.remote_data.repository

import dagger.assisted.AssistedFactory

interface NetworkDataRepository {

    suspend fun getAllCategory()
    suspend fun getAllChannel()
    suspend fun getAllBanner()
    suspend fun getAllTvShows()
}