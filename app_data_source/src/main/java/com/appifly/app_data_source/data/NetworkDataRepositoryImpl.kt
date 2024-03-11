package com.appifly.app_data_source.data

import com.appifly.app_data_source.datamapper.toEntity
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.TvShowDao
import com.appifly.network.apiCall
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import com.appifly.network.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataRepositoryImpl @Inject constructor(
    private val apiService: NetworkCallbackApi,
    private val categoryDao: CategoryDao,
    private val channelDao: ChannelDao,
    private val bannerDao: BannerDao,
    private val tvShowDao: TvShowDao,

    ) : NetworkDataRepository {
    override suspend fun getAllCategory() {
        when (val data = apiCall { apiService.getAllCategory() }) {
            is DataState.Success -> {
                if (data.result.category.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        categoryDao.insert(data.result.category.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }
    }

    override suspend fun getAllChannel() {

        when (val data = apiCall { apiService.getAllChannel() }) {
            is DataState.Success -> {
                if (data.result.channel_list.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        channelDao.insert(data.result.channel_list.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }

    }

    override suspend fun getAllBanner() {

        when (val data = apiCall { apiService.getAllBanner() }) {
            is DataState.Success -> {
                if (data.result.banner.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        bannerDao.insert(data.result.banner.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }
    }

    override suspend fun getAllTvShows() {

        when (val data = apiCall { apiService.getAllTvShows() }) {
            is DataState.Success -> {
                if (data.result.tv_shows.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        tvShowDao.insert(data.result.tv_shows.map { it.toEntity() })
                        print("")
                    }

                }

            }

            is DataState.Error -> {

            }


            else -> {

            }

        }
    }

}