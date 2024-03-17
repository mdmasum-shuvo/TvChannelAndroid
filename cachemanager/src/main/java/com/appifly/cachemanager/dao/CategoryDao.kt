package com.appifly.cachemanager.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appifly.cachemanager.LocalDbConstant
import com.appifly.cachemanager.model.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CategoryEntity>)

    @Query("SELECT * FROM ${LocalDbConstant.CATEGORY_TABLE}")
     fun getAllCategory(): LiveData<List<CategoryEntity>>?


     @Query("SELECT name FROM ${LocalDbConstant.CATEGORY_TABLE} WHERE id=:id")
     suspend fun getCategoryNameById(id:Int): String?

}