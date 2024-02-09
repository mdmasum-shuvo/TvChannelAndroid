package com.appifly.cachemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.model.CategoryEntity
import com.appifly.cachemanager.model.ChannelEntity

@Database(
    entities = [CategoryEntity::class, ChannelEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TvDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun channelDao(): ChannelDao
}