package com.appifly.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
    return try {
        withContext(Dispatchers.IO) {
            val response = call()
            DataState.Success(response)
        }
    } catch (ex: HttpException) {
        when (ex.code()) {
            504 -> DataState.IOError(IOException())
            else -> {
                DataState.Error(ex)
            }
        }
    } catch (ex: IOException) {
        DataState.IOError(ex)
    }

}