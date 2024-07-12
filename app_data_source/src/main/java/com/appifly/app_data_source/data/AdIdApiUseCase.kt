package com.appifly.app_data_source.data

import DefaultResponse
import com.appifly.network.remote_data.HttpParam
import com.appifly.network.remote_data.repository.NetworkDataRepository
import com.appifly.network.sealed.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdIdApiUseCase @Inject constructor(private val repository: NetworkDataRepository) {
    operator fun invoke(): Flow<ResponseResult<DefaultResponse>> =
        flow {
            try {
                emit(ResponseResult.Loading())
                val listResponse = repository.getAllAddId()
                when (listResponse.statusCode) {
                    HttpParam.SUCCESS_STATUS_CODE -> emit(ResponseResult.Success(listResponse))
                    else -> { emit(ResponseResult.Error(listResponse.message ?: "An unexpected error")) }
                }
            } catch (e: Exception) {
                val error = ResponseResult.Error<DefaultResponse>("An unexpected error")
                emit(error)
            }
        }

}