package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.CATEGORY_TABLE)

data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name:String?,
    val imageUrl:String?
)
