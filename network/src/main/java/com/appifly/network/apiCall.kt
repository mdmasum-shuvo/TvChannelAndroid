package com.appifly.network

import com.appinion.network.DataState
import retrofit2.HttpException
import java.io.IOException

suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
    return try {
        val response = call()
        DataState.Success(response)
    } catch (ex: HttpException) {
        when (ex.code()) {
            504 -> DataState.IOError(IOException())
            else -> { DataState.Error(ex) }
        }
    } catch (ex: IOException) {
        DataState.IOError(ex)
    }

}