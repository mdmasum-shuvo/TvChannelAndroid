package com.appifly.app_data_source.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.cachemanager.dao.FavoriteDao
import com.appifly.cachemanager.dao.FrequentlyDao
import com.appifly.cachemanager.model.FavoriteEntity
import com.appifly.cachemanager.model.FrequentlyEntity
import com.appifly.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelDao: ChannelDao,
    private val favoriteDao: FavoriteDao,
    private val frequentlyDao: FrequentlyDao
) : ViewModel() {

    var catId: Int = 0

    private val _channelData = MutableLiveData<List<ChannelDto>>()
    val channelData: LiveData<List<ChannelDto>>
        get() = _channelData
    private val _isFavoriteChannel = MutableLiveData<Boolean>(false)
    val isFavoriteChannel: LiveData<Boolean>
        get() = _isFavoriteChannel


    private val _selectedChannel = MutableLiveData<ChannelDto>()
    val selectedChannel: LiveData<ChannelDto>
        get() = _selectedChannel


    val popularChannelList = channelDao.getPopularChannel()?.map { it -> it.map { it.toDto() } }
    val favoriteChannelList =
        favoriteDao.getAllFavoriteChannel().map { it -> it.map { it.toDto() } }

    val frequentlyPlayedChannelList =
        frequentlyDao.getAllFrequentlyPlayedChannel().map { it -> it.map { it.toDto() } }


    fun callChannelDataByCatId() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _channelData.value = channelDao.getAllChannelByCategory(catId).map { it.toDto() }
            }
        }
        Log.e("data", channelData.value.toString())
    }

    fun setFavoriteChannel(channelDto: ChannelDto) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val countRow = favoriteDao.countRow(channelDto.id!!)
                if (countRow == 0.toLong()) {
                    val count = favoriteDao.insert(FavoriteEntity(channelId = channelDto.id!!))
                    if (count >= 1) {
                        withContext(Dispatchers.Main) {
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
            withContext(Dispatchers.IO) {
                val countRow = favoriteDao.countRow(channelId)
                if (countRow >= 1) {
                    withContext(Dispatchers.Main) {
                        _isFavoriteChannel.value = true
                    }
                }
                // callChannelDataByCatId()
            }
        }
    }

    fun addTOFrequentChannel(chanelId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
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
            withContext(Dispatchers.IO) {
                val countRow = favoriteDao.deleteItem(chanelId)
                if (countRow >= 1) {
                    withContext(Dispatchers.Main) {
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