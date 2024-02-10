package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.cachemanager.dao.CategoryDao
import com.appifly.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryListUseCase,
      categoryDao: CategoryDao
) :
    ViewModel() {

    val categoryData = categoryDao.getAllCategory()

    init {
        getCategoryData()
    }

    private fun getCategoryData() {
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