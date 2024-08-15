package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.AD_ID_TABLE)
data class AdIdEntity(
    @PrimaryKey
    val id: Int,
    val fbBanner: String?,
    val fbInterstitial: String?,
    val admobBanner: String?,
    val admobInterstitial: String?
)