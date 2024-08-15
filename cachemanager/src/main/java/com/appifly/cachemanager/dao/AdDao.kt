package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.model.AdIdEntity

@Dao
interface AdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: AdIdEntity): Long

    @Query("select * from ad_id_table")
    fun getAllAdID(): LiveData<List<AdIdEntity>>?

    @Transaction
    suspend fun updateData(data: AdIdEntity):Long {
        deleteAll()
        return insertAll(data)
    }
    @Query("DELETE FROM ad_id_table")
    fun deleteAll()
}