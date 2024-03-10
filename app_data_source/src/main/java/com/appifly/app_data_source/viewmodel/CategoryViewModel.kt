package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.cachemanager.dao.CategoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
  categoryDao: CategoryDao,
) : ViewModel() {

    private val _channelCategoryName=MutableLiveData<String>()

    val channelCategoryName: LiveData<String>
        get() = _channelCategoryName
    val categoryData = categoryDao.getAllCategory()?.map { it -> it.map { it.toDto() } }


    fun setCategoryName(categoryName:String?){
        _channelCategoryName.value=categoryName?:"N/A"
    }

}