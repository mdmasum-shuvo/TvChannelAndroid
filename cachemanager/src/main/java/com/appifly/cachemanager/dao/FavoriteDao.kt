package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.model.ChannelEntity
import com.appifly.cachemanager.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity):Long

    @Query("SELECT COUNT(*) FROM FAVORITE_TABLE WHERE channelId=:channelId")
    suspend fun countRow(channelId:Int):Long

    @Query("delete from FAVORITE_TABLE WHERE channelId=:channelId ")
    suspend fun deleteItem(channelId: Int):Int
    @Query("select catId,channel_table.id,name,iconUrl,liveUrl from channel_table  left join favorite_table where favorite_table.channelId=channel_table.id")
     fun getAllFavoriteChannel(): LiveData<List<ChannelEntity>>

     @Query("select catId,channel_table.id,name,iconUrl,liveUrl from channel_table  left join favorite_table where favorite_table.channelId=channel_table.id AND channel_table.catId=:categoryId")
     fun getAllFavoriteChannelByCat(categoryId:Int): LiveData<List<ChannelEntity>>
}