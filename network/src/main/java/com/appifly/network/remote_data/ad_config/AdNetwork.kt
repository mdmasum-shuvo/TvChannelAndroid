package com.appifly.network.remote_data.ad_config

data class AdNetwork(
    val app_id: String?,
    val banner_ad_id: String?,
    val banner_enabled: Boolean?,
    val enabled: Boolean?,
    val interstitial_ad_id: String?,
    val interstitial_enabled: Boolean?,
    val network_name: String?,
    val video_ad_id: String?,
    val video_enabled: Boolean?
)