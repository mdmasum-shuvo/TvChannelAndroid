package com.appifly.app_data_source.data

import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.network.apiCall
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import com.appifly.network.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataRepositoryImpl @Inject constructor(
    private val apiService: NetworkCallbackApi,
    private val categoryDao: CategoryDao,
    private val channelDao: ChannelDao
) : NetworkDataRepository {
    override suspend fun getAllCategory() {
        var data = apiCall { apiService.getAllCategory() }
        when (data) {
            is DataState.Success -> {
                withContext(Dispatchers.IO){

                }

            }

            is DataState.Error -> {

            }

            is DataState.Success -> {

            }

            else -> {

            }

        }
    }

    override suspend fun getAllChannel() {
        var data = apiCall { apiService.getAllChannel() }


    }

}