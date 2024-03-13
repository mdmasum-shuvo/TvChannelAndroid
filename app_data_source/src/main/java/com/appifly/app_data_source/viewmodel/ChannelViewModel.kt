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
    private val channelDao: ChannelDao
) : ViewModel() {

    private val _channelData = MutableLiveData<List<ChannelDto>>()

    var catId:Int=0
    val channelData: LiveData<List<ChannelDto>>
        get() = _channelData 


    val popularChannelList=channelDao.getPopularChannel()?.map { it -> it.map { it.toDto() } }
    //val favoriteChannelList=channelDao.getFavoriteChannel()?.map { it -> it.map { it.toDto() } }


    fun callChannelDataByCatId() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _channelData.value = channelDao.getAllChannelByCategory(catId).map { it.toDto() }
            }
        }
        Log.e("data", channelData.value.toString())
    }



    fun setFavoriteChannel(chanelId:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
              //  channelDao.favoriteChannel(chanelId)
                callChannelDataByCatId()

            }
        }
    }

}