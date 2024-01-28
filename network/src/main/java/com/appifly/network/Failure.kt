package com.appinion.network

import com.appifly.network.HttpStatusCode
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Failure : IOException() {
    object UnknownError : Failure()

    object ConnectivityError : Failure()

    data class UnAuthorizedException(var code: Int = 0, override var message: String) : Failure()

    data class NotFoundException(override var message: String) : Failure()
    data class SocketTimeoutError(override var message: String) : Failure()
    data class UnProcessableError(override var message: String) : Failure()
    data class DataNotFoundError(override var message: String) : Failure()

}

fun Throwable.handleThrowable(): Failure {
    // Timber.e(this)
    return if (this is UnknownHostException) {
        Failure.ConnectivityError
    } else if (this is HttpException && this.code() == HttpStatusCode.Unauthorized.code) {
        Failure.UnAuthorizedException(code = this.code(),this.code().toString())
    } else if (this is HttpException && this.code() == HttpStatusCode.UnProcessable.code) {
        Failure.UnProcessableError(this.code().toString()) 
    }
    else if (this is HttpException && this.code() == HttpStatusCode.NotFound.code) {
        Failure.DataNotFoundError(this.code().toString())
    } else if (this is SocketTimeoutException) {
        Failure.SocketTimeoutError(this.message!!)
    } else if (this.message != null) {
        Failure.NotFoundException(this.message!!)
    } else {
        Failure.UnknownError
    }
}

//fun Exception.toCustomExceptions() = when (this) {
//    is ServerResponseException -> Failure.HttpErrorInternalServerError(this)
//    is ClientRequestException ->
//        when (this.response.status.value) {
//            400 -> Failure.HttpErrorBadRequest(this)
//            401 -> Failure.HttpErrorUnauthorized(this)
//            403 -> Failure.HttpErrorForbidden(this)
//            404 -> Failure.HttpErrorNotFound(this)
//            else -> Failure.HttpError(this)
//        }
//    is RedirectResponseException -> Failure.HttpError(this)
//    else -> Failure.GenericError(this)
//}
