package com.appifly.app_data_source.dto

import androidx.compose.runtime.Immutable

@Immutable
data class CategoryDto(
    val id: Int,
    val name: String?,
    val imageUrl: String?
)