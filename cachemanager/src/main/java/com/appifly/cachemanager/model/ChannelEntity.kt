package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.CHANNEL_TABLE)
data class ChannelEntity (
    @PrimaryKey
    val id: Int,
    val catId:Int,
    val name:String?,
    val iconUrl:String?,
    val liveUrl:String?,
    val isPopular:Boolean?,
    val isFavorite:Boolean=false
)
