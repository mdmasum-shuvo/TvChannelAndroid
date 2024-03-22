package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.di.IoDispatcher
import com.appifly.app_data_source.di.MainDispatcher
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.dao.CategoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val _channelCategoryName = MutableLiveData<String>()

    val channelCategoryName: LiveData<String>
        get() = _channelCategoryName


    val categoryData = categoryDao.getAllCategory()?.map { data -> data.map { it.toDto() } }


    private val _favoriteCategoryList = MutableLiveData<List<CategoryDto>>()

    val favoriteCategoryList: LiveData<List<CategoryDto>>
        get() = _favoriteCategoryList

    fun setCategoryName(categoryName: String?) {
        _channelCategoryName.value = categoryName ?: "N/A"
    }

    fun getCategoryNameById(catId: Int) {
        viewModelScope.launch {
            withContext(mainDispatcher) {
                _channelCategoryName.value = categoryDao.getCategoryNameById(catId)
            }
        }
    }

    fun setFavoriteCategory(favoriteChannel: List<ChannelDto>) {
        val catList = ArrayList<CategoryDto>()
        for (item in favoriteChannel) {
            for (data in categoryData?.value!!) {
                if (item.catId == data.id) {
                    catList.add(data)
                    break
                }
            }
        }

        val removeDuplicate = catList.toHashSet()
        _favoriteCategoryList.value = removeDuplicate.toList()
    }

}