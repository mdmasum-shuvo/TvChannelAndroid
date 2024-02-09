package com.appifly.app_data_source.di

import com.appifly.app_data_source.data.NetworkDataRepositoryImpl
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {


    @Provides
    @ViewModelScoped
    fun provideDataRepository(
        networkCallbackApi: NetworkCallbackApi,
        categoryDao: CategoryDao,
        channelDao: ChannelDao
    ): NetworkDataRepository {
        return NetworkDataRepositoryImpl(networkCallbackApi, categoryDao, channelDao)
    }
}