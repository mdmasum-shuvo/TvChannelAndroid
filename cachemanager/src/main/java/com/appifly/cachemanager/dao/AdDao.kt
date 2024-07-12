package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.AdIdEntity

interface AdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: AdIdEntity): Long

    @Query("select * from AD_ID_TABLE")
    fun getAllAdID(): LiveData<AdIdEntity>?

    @Transaction
    suspend fun updateData(data: AdIdEntity):Long {
        deleteAll()
        return insertAll(data)
    }



    @Query("DELETE FROM AD_ID_TABLE")
    fun deleteAll()
}