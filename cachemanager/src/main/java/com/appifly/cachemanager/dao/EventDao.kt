package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.EventChannelJoin
import com.appifly.cachemanager.model.EventEntity

@Dao
interface EventDao {

    // Insert a list of EventEntity with conflict strategy as REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<EventEntity>): List<Long>

    // Select all events and return LiveData
    @Query("SELECT event_table.teamOneName,event_table.teamTwoName,event_table.startTime,event_table.teamOneImageUrl,event_table.teamTwoImageUrl,event_table.channelListId,channel_table.name, channel_table.liveUrl ,channel_table.liveChannelReferer ,channel_table.iconUrl ,channel_table.catId FROM event_table LEFT JOIN channel_table Where event_table.channelListId=channel_table.id")
    fun getAllEvents(): LiveData<List<EventChannelJoin>?>

    @Transaction
    suspend fun updateData(data: List<EventEntity>): List<Long> {
        deleteAll()
        return insertAll(data)
    }

    @Query("DELETE FROM event_table")
    fun deleteAll()
}
