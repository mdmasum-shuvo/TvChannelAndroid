package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appifly.cachemanager.LocalDbConstant
import com.appifly.cachemanager.model.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CategoryEntity>):List<Long>

    @Query("SELECT * FROM ${LocalDbConstant.CATEGORY_TABLE}")
     fun getAllCategory(): LiveData<List<CategoryEntity>>?


     @Query("SELECT name FROM ${LocalDbConstant.CATEGORY_TABLE} WHERE id=:id")
     suspend fun getCategoryNameById(id:Int): String?

    @Transaction
    suspend fun updateData(data: List<CategoryEntity>): List<Long> {
        deleteAll()
        return insertAll(data)
    }



    @Query("DELETE FROM CATEGORY_TABLE")
    fun deleteAll()
}