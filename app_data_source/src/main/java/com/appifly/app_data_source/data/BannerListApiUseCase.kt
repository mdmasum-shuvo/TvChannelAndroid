package com.appifly.app_data_source.data

import com.appifly.network.DataState
import com.appifly.network.remote_data.banner.BannerNetwork
import com.appifly.network.remote_data.model.category.CategoryNetwork
import com.appifly.network.remote_data.repository.NetworkDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BannerListApiUseCase @Inject constructor(private val repository: NetworkDataRepository) {
    operator fun invoke(): Flow<DataState<BannerNetwork>> =
        flow {
            emit(DataState.Loading())
            repository.getAllBanner()
            emit(DataState.DisableLoading())
        }

}