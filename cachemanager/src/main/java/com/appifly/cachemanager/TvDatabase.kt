package com.appifly.cachemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.FrequentlyDao
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.FrequentlyEntity

@Database(
    entities = [CategoryEntity::class, ChannelEntity::class, FrequentlyEntity::class,],
    version = 11,
    exportSchema = false
)
abstract class TvDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun channelDao(): ChannelDao
    abstract fun frequentlyDao(): FrequentlyDao
}