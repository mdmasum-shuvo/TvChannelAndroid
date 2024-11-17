package com.appifly.app_data_source.dto

data class AdIdDto(
    val id : Int,
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