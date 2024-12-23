package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.TvShowChannelJoin
import com.appifly.cachemanager.model.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<TvShowEntity>): List<Long>

    @Query("select  tv_show_table.imageUrl,tv_show_table.date,tv_show_table.title,tv_show_table.channelId,channel_table.iconUrl ,channel_table.catId,channel_table.name ,channel_table.liveUrl from tv_show_table  LEFT JOIN channel_table Where tv_show_table.channelId=channel_table.id")
    fun getAllTvShow(): LiveData<List<TvShowChannelJoin>>?

    @Transaction
    suspend fun updateData(data: List<TvShowEntity>): List<Long> {
        deleteAll()
        return insertAll(data)
    }



    @Query("DELETE FROM TV_SHOW_TABLE")
    fun deleteAll()
}