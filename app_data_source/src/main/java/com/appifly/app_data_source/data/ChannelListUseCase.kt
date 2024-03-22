package com.appifly.app_data_source.data

import com.appifly.network.DataState
import com.appifly.network.remote_data.model.channel.ChannelNetwork
import com.appifly.network.remote_data.repository.NetworkDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChannelListUseCase @Inject constructor(private val repository: NetworkDataRepository) {
    operator fun invoke(): Flow<DataState<ChannelNetwork>> =
        flow {
            repository.getAllChannel()
        }

}