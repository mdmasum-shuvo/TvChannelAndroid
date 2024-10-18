package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.cachemanager.dao.AdDao
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.EventDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    eventDao: EventDao, bannerDao: BannerDao, adDao: AdDao
) : ViewModel() {

    var selectedIndex: Int = 0
    val bannerListLiveData = bannerDao.getAllBanner()?.map { banner -> banner.map { it.toDto() } }
    val eventListLiveData = eventDao.getAllEvents().map {data-> data?.map { it.toDto() } }
    val adIdData = adDao.getAllAdID()?.map { adData -> adData.map { it.toDto() }}

}