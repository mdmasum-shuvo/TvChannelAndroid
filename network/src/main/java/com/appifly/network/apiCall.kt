package com.appifly.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T : Any> apiCall(call: suspend () -> T,ioDispatcher: CoroutineDispatcher): DataState<T> {
    return try {
        withContext(ioDispatcher) {
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