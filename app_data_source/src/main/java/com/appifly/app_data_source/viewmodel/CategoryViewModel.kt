package com.appifly.app_data_source.viewmodel

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

    val categoryData = categoryDao.getAllCategory().map { it -> it.map { it.toDto() } }

}