package com.appifly.app_data_source.di

import com.appifly.app_data_source.data.NetworkDataRepositoryImpl
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.TvShowDao
import com.appifly.network.remote_data.NetworkCallbackApi
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
        bannerDao: BannerDao, tvShowDao: TvShowDao
    ): NetworkDataRepository {
        return NetworkDataRepositoryImpl(
            networkCallbackApi,
            categoryDao,
            channelDao,
            bannerDao,
            tvShowDao
        )
    }


}