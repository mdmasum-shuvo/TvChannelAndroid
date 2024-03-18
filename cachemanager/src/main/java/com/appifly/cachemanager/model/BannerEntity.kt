package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.BANNER_TABLE)
data class BannerEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: String?,
    val imageUrl: String?,
    val channelId: Int
)

data class BannerChannelJoin(
    val title: String,
    val date: String?,
    val imageUrl: String?,
    val channelId: Int,
    val name:String,
    val catId:Int,
    val iconUrl: String,
    val liveUrl: String
)
