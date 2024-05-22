package com.appifly.network.remote_data.model.category

data class CategoryResponse(
    val status: Int,
    val message: String,
    val data: List<CategoryNetwork>
)