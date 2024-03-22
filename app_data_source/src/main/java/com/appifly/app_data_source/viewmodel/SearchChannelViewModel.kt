package com.appifly.app_data_source.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.di.IoDispatcher
import com.appifly.app_data_source.di.MainDispatcher
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.dao.ChannelDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchChannelViewModel @Inject constructor(
    private val channelDao: ChannelDao,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
):ViewModel() {
    private val _channelData = MutableLiveData<List<ChannelDto>>()
    val channelData: LiveData<List<ChannelDto>>
        get() = _channelData


    fun searchChannel(keyword:String){
        viewModelScope.launch {
            withContext(mainDispatcher){
                _channelData.value=channelDao.searchChannel(keyword)?.map { it.toDto() }

                Log.e("search",channelData.value.toString())

            }
        }
    }

}