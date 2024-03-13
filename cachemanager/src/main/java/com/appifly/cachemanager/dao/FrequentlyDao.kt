package com.appifly.cachemanager.dao

import androidx.room.Dao
import com.appifly.cachemanager.model.FrequentlyEntity

@Dao
interface FrequentlyDao {
    suspend fun insert(frequentlyEntity: FrequentlyEntity):Long

}