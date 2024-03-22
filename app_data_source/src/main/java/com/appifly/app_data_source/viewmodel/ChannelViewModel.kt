package com.appifly.app_data_source.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.di.IoDispatcher
import com.appifly.app_data_source.di.MainDispatcher
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.FavoriteDao
import com.appifly.cachemanager.dao.FrequentlyDao
import com.appifly.cachemanager.model.FavoriteEntity
import com.appifly.cachemanager.model.FrequentlyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelDao: ChannelDao,
    private val favoriteDao: FavoriteDao,
    private val frequentlyDao: FrequentlyDao,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

) : ViewModel() {

    var catId: Int = 0

    private val _channelData = MutableLiveData<List<ChannelDto>>()
    val channelData: LiveData<List<ChannelDto>>
        get() = _channelData
    private val _isFavoriteChannel = MutableLiveData(false)
    val isFavoriteChannel: LiveData<Boolean>
        get() = _isFavoriteChannel


    private val _selectedChannel = MutableLiveData<ChannelDto>()
    val selectedChannel: LiveData<ChannelDto>
        get() = _selectedChannel


    val popularChannelList = channelDao.getPopularChannel()?.map { it.map { data-> data.toDto() } }
    val favoriteChannelList =
        favoriteDao.getAllFavoriteChannel().map { it.map {data-> data.toDto() } }

    val frequentlyPlayedChannelList =
        frequentlyDao.getAllFrequentlyPlayedChannel().map { it.map { data-> data.toDto() } }

    fun callChannelDataByCatId() {
        viewModelScope.launch {
            withContext(mainDispatcher) {
                _channelData.value = channelDao.getAllChannelByCategory(catId).map { it.toDto() }
            }
        }
        Log.e("data", channelData.value.toString())
    }

    fun setFavoriteChannel(channelDto: ChannelDto) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val countRow = favoriteDao.countRow(channelDto.id!!)
                if (countRow == 0.toLong()) {
                    val count = favoriteDao.insert(FavoriteEntity(channelId = channelDto.id!!))
                    if (count >= 1) {
                        withContext(mainDispatcher) {
                            _isFavoriteChannel.value = true
                        }
                    }
                }
                // callChannelDataByCatId()
            }
        }
    }

    fun checkFavorite(channelId: Int) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val countRow = favoriteDao.countRow(channelId)
                if (countRow >= 1) {
                    withContext(mainDispatcher) {
                        _isFavoriteChannel.value = true
                    }
                } else {
                    withContext(mainDispatcher) {
                        _isFavoriteChannel.value = false
                    }
                }
                // callChannelDataByCatId()
            }
        }
    }

    fun addTOFrequentChannel(chanelId: Int) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val countRow = frequentlyDao.countRow(chanelId)
                if (countRow == 0.toLong()) {
                    frequentlyDao.insert(FrequentlyEntity(channelId = chanelId))
                }
                // callChannelDataByCatId()
            }
        }
    }

    fun removeFavoriteChannel(chanelId: Int) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val countRow = favoriteDao.deleteItem(chanelId)
                if (countRow >= 1) {
                    withContext(mainDispatcher) {
                        _isFavoriteChannel.value = false
                    }
                }
                // callChannelDataByCatId()
            }
        }
    }

    fun setSelectedChannel(item: ChannelDto) {
        _selectedChannel.value = item
    }

}