package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.BannerChannelJoin
import com.appifly.cachemanager.model.BannerEntity

@Dao
interface BannerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BannerEntity>): List<Long>

    @Query("select  banner_table.imageUrl,banner_table.date,banner_table.title,banner_table.channelId,channel_table.iconUrl,channel_table.catId,channel_table.name ,channel_table.liveUrl from banner_table  LEFT JOIN channel_table Where banner_table.channelId=channel_table.id")
    fun getAllBanner(): LiveData<List<BannerChannelJoin>>?

    @Transaction
    suspend fun updateData(data: List<BannerEntity>): List<Long> {
        deleteAll()
        return insertAll(data)
    }



    @Query("DELETE FROM BANNER_TABLE")
    fun deleteAll()
}