package com.appifly.app_data_source.data

import DefaultResponse
import com.appifly.app_data_source.datamapper.toEntity
import com.appifly.app_data_source.di.IoDispatcher
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.network.DataState
import com.appifly.network.apiCall
import com.appifly.network.remote_data.HttpParam
import com.appifly.network.remote_data.HttpParam.SUCCESSFUL_TEXT
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NetworkDataRepositoryImpl @Inject constructor(
    private val apiService: NetworkCallbackApi,
    private val categoryDao: CategoryDao,
    private val channelDao: ChannelDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

) : NetworkDataRepository {
    override suspend fun getAllCategory():DefaultResponse {
        val responseData = DefaultResponse(statusCode = HttpParam.SUCCESS_STATUS_CODE, message = SUCCESSFUL_TEXT)

        when (val data = apiCall({ apiService.getAllCategory() },ioDispatcher)) {
            is DataState.Success -> {
                if (data.result.data.isNotEmpty()) {
                    withContext(ioDispatcher) {
                        categoryDao.updateData(data.result.data.map { it.toEntity() })
                        print("")
                    }

                }
                return responseData
            }

            is DataState.Error -> {
                return responseData.copy(HttpParam.ERROR_STATUS_CODE,HttpParam.SERVER_NOT_FOUND_EXCEPTION)

            }

            is DataState.IOError -> {
                return responseData.copy(HttpParam.ERROR_STATUS_CODE,HttpParam.SERVER_NOT_FOUND_EXCEPTION)
            }

        }
    }

    override suspend fun getAllChannel():DefaultResponse {
        val responseData = DefaultResponse(statusCode =HttpParam.SUCCESS_STATUS_CODE, message = SUCCESSFUL_TEXT)

        when (val data = apiCall( { apiService.getAllChannel() },ioDispatcher)) {
            is DataState.Success -> {
                if (data.result.data.isNotEmpty()) {
                    withContext(ioDispatcher) {
                        channelDao.updateData(data.result.data.map { it.toEntity() })
                        print("")
                    }

                }
                return responseData
            }

            is DataState.Error -> {
                return responseData.copy(HttpParam.ERROR_STATUS_CODE,HttpParam.SERVER_NOT_FOUND_EXCEPTION)

            }

            is DataState.IOError -> {
                return responseData.copy(HttpParam.ERROR_STATUS_CODE,HttpParam.SERVER_NOT_FOUND_EXCEPTION)

            }

        }

    }


}