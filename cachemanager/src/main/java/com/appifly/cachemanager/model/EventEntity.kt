package com.appifly.cachemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appifly.cachemanager.LocalDbConstant.EVENT_TABLE

@Entity(tableName = EVENT_TABLE)
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val channelListId: Int?,
    val endTime: String?,
    val startTime: String?,
    val status: Int?,
    val teamOneName: String?,
    val teamTwoName: String?,
    val teamOneImageUrl: String?,
    val teamTwoImageUrl: String?
)
