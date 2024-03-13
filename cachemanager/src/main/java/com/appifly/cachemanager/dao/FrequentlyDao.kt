package com.appifly.cachemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.appifly.cachemanager.model.FrequentlyEntity

@Dao
interface FrequentlyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(frequentlyEntity: FrequentlyEntity):Long

}