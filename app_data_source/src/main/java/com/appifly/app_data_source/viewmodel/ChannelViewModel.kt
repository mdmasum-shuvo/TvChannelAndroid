package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.cachemanager.dao.ChannelDao
import com.appifly.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val useCase: ChannelListUseCase,
    channelDao: ChannelDao
) : ViewModel()  {


    val channelData = channelDao.getAllChannel()

    init {
        getChannelData()
    }

    private fun getChannelData() {
        useCase.invoke().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {

                }

                is DataState.DisableLoading -> {

                }

                else -> {

                }

            }

        }.launchIn(viewModelScope)
    }
}