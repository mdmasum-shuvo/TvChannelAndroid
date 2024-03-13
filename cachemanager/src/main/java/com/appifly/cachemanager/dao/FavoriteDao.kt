package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity):Long

    @Query("select catId,channel_table.id,name,iconUrl,liveUrl from channel_table  left join favorite_table where favorite_table.channelId=channel_table.id")
     fun getAllFavoriteChannel(): LiveData<List<ChannelEntity>>
}