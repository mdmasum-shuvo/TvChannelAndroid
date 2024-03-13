package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.appifly.cachemanager.model.FavoriteEntity

@Dao
interface FavoriteDao {

    suspend fun insert(favoriteEntity: FavoriteEntity):Long

  /*  @Query("SELECT * FROM CHANNEL_TABLE LEFT JOIN CHANNEL_TABLE WHERE CHANNEL_TABLE.id =favorite_table.chnnel_id")
    suspend fun getAllFavoriteChannel():LiveData<List<FavoriteEntity>>*/
}