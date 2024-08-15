package com.appifly.app_data_source.datamapper

import com.appifly.app_data_source.dto.AdIdDto
import com.appifly.cachemanager.model.AdIdEntity
import com.appifly.network.remote_data.ad.AdIdData

fun AdIdData.toEntity(): AdIdEntity {

    return AdIdEntity(
        id = id,
        fbBanner = fb_banneradID,
        fbInterstitial = fb_interstitialadID,
        admobBanner = banneradID,
        admobInterstitial = interstitialadID,

        )
}


fun AdIdEntity.toDto(): AdIdDto {
    return AdIdDto(
        id = id,
        admobBanner = admobBanner,
        admobInterstitial = admobInterstitial,
        fbBanner = fbBanner,
        fbInterstitial = fbInterstitial,
    )
}
