package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.FAVORITE_TABLE)
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val channelId: Int,
)
