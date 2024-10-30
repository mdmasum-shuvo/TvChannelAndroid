package com.appifly.cachemanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.LocalDbConstant
import com.appifly.cachemanager.model.ChannelEntity

@Dao
interface ChannelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ChannelEntity>):List<Long>

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE} WHERE name LIKE  '%' || :search || '%'  ")
    suspend fun searchChannel(search:String): List<ChannelEntity>?

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE} WHERE isPopular=1")
    fun getPopularChannel():List<ChannelEntity>?

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE} WHERE catId=:categoryId")
    suspend fun getAllChannelByCategory(categoryId: Int): List<ChannelEntity>

    @Query("SELECT * FROM ${LocalDbConstant.CHANNEL_TABLE}")
    suspend fun getAllChannel(): List<ChannelEntity>


    @Transaction
    suspend fun updateData(users: List<ChannelEntity>): List<Long> {
        deleteAll()
        return insertAll(users)
    }

    @Query("DELETE FROM CHANNEL_TABLE")
    fun deleteAll()

}