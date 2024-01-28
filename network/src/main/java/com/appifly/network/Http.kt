package com.appifly.network

enum class HttpStatusCode(val code: Int) {
    Unauthorized(401),
    Validation(401),
    NotFound(404),
    UnProcessable(422),

}