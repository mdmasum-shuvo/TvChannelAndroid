package com.appifly.cachemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.FavoriteDao
import com.appifly.cachemanager.dao.FrequentlyDao
import com.appifly.cachemanager.dao.TvShowDao
import com.appifly.cachemanager.model.BannerEntity
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.FavoriteEntity
import com.appifly.cachemanager.model.FrequentlyEntity
import com.appifly.cachemanager.model.TvShowEntity

@Database(
    entities = [CategoryEntity::class, ChannelEntity::class, BannerEntity::class, TvShowEntity::class, FavoriteEntity::class, FrequentlyEntity::class],
    version = 6,
    exportSchema = true
)
abstract class TvDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun channelDao(): ChannelDao
    abstract fun bannerDao(): BannerDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun frequentlyDao(): FrequentlyDao
}