package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.EventEntity

@Dao
interface EventDao {

    // Insert a list of EventEntity with conflict strategy as REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<EventEntity>): List<Long>

    // Select all events and return LiveData
    @Query("SELECT * FROM event_table")
    fun getAllEvents(): LiveData<List<EventEntity>>

    @Transaction
    suspend fun updateData(data: List<EventEntity>): List<Long> {
        deleteAll()
        return insertAll(data)
    }

    @Query("DELETE FROM event_table")
    fun deleteAll()
}
