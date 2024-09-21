package com.appifly.tvchannel.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ruben Quadros on 07/07/22
 **/
@HiltViewModel
class MainActivityViewModel @Inject constructor(handle: SavedStateHandle) :
    ViewModel(){

    var _uiState=MutableLiveData<MainState>()

    val uiState :LiveData<MainState> get() = _uiState

    fun toggleFullScreen(isFullScreen: Boolean) = run { _uiState.value= MainState(isFullScreen) }
}