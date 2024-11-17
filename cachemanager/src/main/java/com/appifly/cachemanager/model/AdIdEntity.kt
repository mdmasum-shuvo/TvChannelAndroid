package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.AD_ID_TABLE)
data class AdIdEntity(
    @PrimaryKey
    val id: Int,
    val appId: String?,
    val bannerAdId: String?,
    val bannerEnabled: Boolean?,
    val enabled: Boolean?,
    val interstitialAdId: String?,
    val interstitialEnabled: Boolean?,
    val networkName: String?,
    val videoAdId: String?,
    val videoEnabled: Boolean?,

    )