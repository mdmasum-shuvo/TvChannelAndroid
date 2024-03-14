package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.FrequentlyEntity

@Dao
interface FrequentlyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(frequentlyEntity: FrequentlyEntity):Long

    @Query("SELECT COUNT(*) FROM FREQUENTLY_PLAYED_TABLE WHERE channelId=:channelId")
    suspend fun countRow(channelId:Int):Long

    @Query("select catId,channel_table.id,name,iconUrl,liveUrl from channel_table  left join frequently_played_table where frequently_played_table.channelId=channel_table.id")
    fun getAllFrequentlyPlayedChannel(): LiveData<List<ChannelEntity>>

}