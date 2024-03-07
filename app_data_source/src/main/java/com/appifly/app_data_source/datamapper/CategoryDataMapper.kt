package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.network.BuildConfig
import com.appifly.network.remote_data.model.category.CategoryNetwork


fun CategoryNetwork.toEntity(): CategoryEntity {

    return CategoryEntity(
        id = category_id,
        name = category_title,
        imageUrl = getSubString(image_url)
    )
}

fun CategoryEntity.toDto(): CategoryDto {
    return CategoryDto(
        id = id,
        name = name,
        imageUrl = BuildConfig.ICON_BASE_URL_DRIVE + imageUrl
    )
}

