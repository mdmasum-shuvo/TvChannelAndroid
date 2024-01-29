package com.appifly.network.remote_data.repository

import com.appifly.network.remote_data.model.CategoryNetwork
import com.appifly.network.remote_data.model.ChannelNetwork

interface NetworkDataRepository {

    suspend fun getAllCategory():List<CategoryNetwork>
    suspend fun getAllChannel():List<ChannelNetwork>
}