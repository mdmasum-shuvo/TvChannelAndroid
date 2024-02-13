package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant
import com.google.android.material.bottomsheet.BottomSheetBehavior.StableState

@Entity(tableName = LocalDbConstant.CATEGORY_TABLE)
@StableState
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name:String?,
    val imageUrl:String?
)
