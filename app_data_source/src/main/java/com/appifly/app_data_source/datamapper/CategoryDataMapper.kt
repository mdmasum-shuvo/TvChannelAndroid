package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.network.remote_data.model.category.CategoryNetwork


fun CategoryNetwork.toEntity(): CategoryEntity {

    return CategoryEntity(
        id = id,
        name = name,
        imageUrl = image_url
    )
}

fun CategoryEntity.toDto(): CategoryDto {
    return CategoryDto(
        id = id,
        name = name,
        imageUrl =  imageUrl
    )
}

