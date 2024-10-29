package com.appifly.app_data_source.di

import com.appifly.app_data_source.data.NetworkDataRepositoryImpl
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideDataRepository(
        networkCallbackApi: NetworkCallbackApi,
        categoryDao: CategoryDao,
        channelDao: ChannelDao,
    ): NetworkDataRepository {
        return NetworkDataRepositoryImpl(
            networkCallbackApi,
            categoryDao,
            channelDao
        )
    }


}