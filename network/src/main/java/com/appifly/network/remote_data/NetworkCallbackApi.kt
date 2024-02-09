package com.appifly.network.remote_data

import com.appifly.network.remote_data.model.CategoryNetwork
import com.appifly.network.remote_data.model.ChannelNetwork

interface NetworkCallbackApi {

    @()
    suspend fun getAllCategory():List<CategoryNetwork>
    suspend fun getAllChannel():List<ChannelNetwork>
}