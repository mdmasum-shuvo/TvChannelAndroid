package com.appifly.cachemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.TvShowDao
import com.appifly.cachemanager.model.BannerEntity
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.TvShowEntity

@Database(
    entities = [CategoryEntity::class, ChannelEntity::class, BannerEntity::class, TvShowEntity::class],
    version = 5,
    exportSchema = true
)
abstract class TvDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun channelDao(): ChannelDao
    abstract fun bannerDao():BannerDao
    abstract fun tvShowDao():TvShowDao
}