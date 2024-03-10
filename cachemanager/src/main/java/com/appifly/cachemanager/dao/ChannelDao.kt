package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.LocalDbConstant
import com.appifly.cachemanager.model.ChannelEntity

@Dao
interface ChannelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ChannelEntity>)

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE}")
     fun getAllChannel(): LiveData<List<ChannelEntity>>?

     @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE} WHERE isPopular=1")
     fun getPopularChannel(): LiveData<List<ChannelEntity>>?

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE} WHERE catId=:categoryId")
     suspend fun getAllChannelByCategory(categoryId:Int): List<ChannelEntity>

}