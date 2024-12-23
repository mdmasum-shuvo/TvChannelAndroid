package com.appifly.cachemanager

import android.content.Context
import androidx.room.Room
import com.appifly.cachemanager.dao.AdDao
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.FavoriteDao
import com.appifly.cachemanager.dao.FrequentlyDao
import com.appifly.cachemanager.dao.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
object LocalCashModule {

    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): TvDatabase {
        return Room.databaseBuilder(context, TvDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: TvDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideAllChannelDao(database: TvDatabase): ChannelDao {
        return database.channelDao()
    }

    @Provides
    @Singleton
    fun provideBannerDao(database: TvDatabase): BannerDao {
        return database.bannerDao()
    }

    @Provides
    @Singleton
    fun provideTvShowDao(database: TvDatabase): TvShowDao {
        return database.tvShowDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: TvDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideFrequentlyDao(database: TvDatabase): FrequentlyDao {
        return database.frequentlyDao()
    }

    @Provides
    @Singleton
    fun provideAdDao(database: TvDatabase): AdDao {
        return database.adDao()
    }


}