package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.AdIdDto
import com.appifly.cachemanager.model.AdIdEntity
import com.appifly.network.remote_data.ad_config.AdNetwork

fun AdNetwork.toEntity(): AdIdEntity {
    return AdIdEntity(
        id = 0,
        appId = this.app_id,
        bannerAdId = this.banner_ad_id,
        bannerEnabled = this.banner_enabled,
        enabled = this.enabled,
        interstitialAdId = this.interstitial_ad_id,
        interstitialEnabled = this.interstitial_enabled,
        networkName = this.network_name,
        videoAdId = this.video_ad_id,
        videoEnabled = this.video_enabled
    )
}


fun AdIdEntity.toDto(): AdIdDto {
    return AdIdDto(
        id = id,
        appId = this.appId,
        bannerAdId = this.bannerAdId,
        bannerEnabled = this.bannerEnabled,
        enabled = this.enabled,
        interstitialAdId = this.interstitialAdId,
        interstitialEnabled = this.interstitialEnabled,
        networkName = this.networkName,
        videoAdId = this.videoAdId,
        videoEnabled = this.videoEnabled
    )
}
