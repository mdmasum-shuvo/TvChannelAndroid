package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appifly.app_data_source.dto.ChannelDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllChannelViewModel @Inject constructor() :ViewModel() {

    private val _channelData = MutableLiveData<List<ChannelDto>>()
    val channelData: LiveData<List<ChannelDto>>
        get() = _channelData

    var dataListTitle:String=""

    fun setSeeAllChannelList(list: List<ChannelDto>,value:String){
        dataListTitle=value
        _channelData.value=list
    }
}