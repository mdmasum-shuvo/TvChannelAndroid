package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.FREQUENTLY_TABLE)
data class FrequentlyEntity(
    @PrimaryKey
    val id: Int,
    val channelId: Int,
)
