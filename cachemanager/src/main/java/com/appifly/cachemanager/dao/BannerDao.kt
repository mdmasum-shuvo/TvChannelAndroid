package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.model.BannerEntity
import com.appifly.cachemanager.model.BannerChannelJoin

@Dao
interface BannerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<BannerEntity>)

    @Query("select  banner_table.imageUrl,banner_table.date,banner_table.title,banner_table.channelId,channel_table.iconUrl from banner_table  LEFT JOIN channel_table Where banner_table.channelId=channel_table.id")
    fun getAllBanner(): LiveData<List<BannerChannelJoin>>?
}