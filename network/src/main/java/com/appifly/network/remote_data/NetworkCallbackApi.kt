package com.appifly.network.remote_data

import com.appifly.network.remote_data.model.category.CategoryResponse
import com.appifly.network.remote_data.model.channel.ChannelResponse
import retrofit2.http.GET

interface NetworkCallbackApi {

    @GET(HttpParam.CATEGORY)
    suspend fun getAllCategory(): CategoryResponse

    @GET(HttpParam.CHANNEL_LIST)
    suspend fun getAllChannel(): ChannelResponse

}