package com.appifly.network

import retrofit2.HttpException
import java.io.IOException

sealed class DataState<out T> {

    data class Success<out T>(val result: T) : DataState<T>()
    data class Error(val error: HttpException) : DataState<Nothing>()
    data class IOError(val ioException: IOException) : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Error -> "Error[exception=$error]"
            is IOError -> "IOError[exception=$ioException]"

        }
    }
    inline fun <R : Any> map(transform: (T) -> R): DataState<R> {
        return when (this) {
            is Error -> Error(this.error)
            is IOError -> IOError(this.ioException)
            is Success -> Success(transform(this.result))
        }
    }

}
