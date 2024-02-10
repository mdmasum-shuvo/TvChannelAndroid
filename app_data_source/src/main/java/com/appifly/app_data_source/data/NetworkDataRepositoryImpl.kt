package com.appifly.app_data_source.data

import com.appifly.app_data_source.datamapper.toEntity
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
                if (data.result.category.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        categoryDao.insert(data.result.category.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }
    }

    override suspend fun getAllChannel() {
        var data = apiCall { apiService.getAllChannel() }

        when (data) {
            is DataState.Success -> {
                if (data.result.channel_list.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        channelDao.insert(data.result.channel_list.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }

    }

}