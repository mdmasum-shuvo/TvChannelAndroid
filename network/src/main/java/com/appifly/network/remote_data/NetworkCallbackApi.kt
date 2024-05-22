package com.appifly.network.remote_data

import com.appifly.network.remote_data.banner.BannerResponse
import com.appifly.network.remote_data.model.category.CategoryResponse
import com.appifly.network.remote_data.model.channel.ChannelResponse
import com.appifly.network.remote_data.model.tv_shows.TvShowsResponse
import retrofit2.http.GET

interface NetworkCallbackApi {

    @GET(HttpParam.CATEGORY)
    suspend fun getAllCategory(): CategoryResponse

    @GET(HttpParam.CHANNEL_LIST)
    suspend fun getAllChannel(): ChannelResponse

    @GET(HttpParam.BANNER)
    suspend fun getAllBanner(): BannerResponse

    @GET(HttpParam.TV_SHOWS)
    suspend fun getAllTvShows(): TvShowsResponse
}