package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.LocalDbConstant
import com.appifly.cachemanager.model.TvShowChannelJoin
import com.appifly.cachemanager.model.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<TvShowEntity>)

    @Query("select  tv_show_table.imageUrl,tv_show_table.date,tv_show_table.title,tv_show_table.channelId,channel_table.iconUrl ,channel_table.catId,channel_table.name ,channel_table.liveUrl from tv_show_table  LEFT JOIN channel_table Where tv_show_table.channelId=channel_table.id")
    fun getAllTvShow(): LiveData<List<TvShowChannelJoin>>?
}