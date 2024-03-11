package com.appifly.network.remote_data

import com.appifly.network.BuildConfig
import com.appifly.network.remote_data.banner.BannerResponse
import com.appifly.network.remote_data.model.category.CategoryResponse
import com.appifly.network.remote_data.model.channel.ChannelResponse
import com.appifly.network.remote_data.model.tv_shows.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCallbackApi {

    @GET(HttpParam.SHEET_API_END_POINT)
    suspend fun getAllCategory(
        @Query("id") sheetId: String = BuildConfig.SHEET_ID,
        @Query("sheet") sheetName: String = HttpParam.SHEET_NAME_CATEGORY
    ): CategoryResponse

    @GET(HttpParam.SHEET_API_END_POINT)
    suspend fun getAllChannel(
        @Query("id") sheetId: String = BuildConfig.SHEET_ID,
        @Query("sheet") sheetName: String = HttpParam.SHEET_NAME_CHANNEL_LIST
    ): ChannelResponse

    @GET(HttpParam.SHEET_API_END_POINT)
    suspend fun getAllBanner(
        @Query("id") sheetId: String = BuildConfig.SHEET_ID,
        @Query("sheet") sheetName: String = HttpParam.SHEET_NAME_BANNER
    ): BannerResponse

    @GET(HttpParam.SHEET_API_END_POINT)
    suspend fun getAllTvShows(
        @Query("id") sheetId: String = BuildConfig.SHEET_ID,
        @Query("sheet") sheetName: String = HttpParam.SHEET_NAME_TV_SHOWS
    ): TvShowsResponse
}