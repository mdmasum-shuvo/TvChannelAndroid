package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant

@Entity(tableName = LocalDbConstant.TV_SHOW_TABLE)

data class TvShowEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: String?,
    val imageUrl: String?,
    val channelId: Int
)

data class TvShowChannelJoin(
    val title: String,
    val date: String?,
    val imageUrl: String,
    val channelId: Int,
    val iconUrl:String,
    val name:String,
    val catId:Int,
    val liveUrl: String
)