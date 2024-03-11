package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.cachemanager.dao.BannerDao
import com.appifly.cachemanager.dao.TvShowDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    tvShowDao: TvShowDao, bannerDao: BannerDao
) : ViewModel() {


    val bannerListLiveData = bannerDao.getAllBanner()?.map { banner -> banner.map { it.toDto() } }
    val tvShowListLiveData = tvShowDao.getAllTvShow()?.map { tvShow -> tvShow.map { it.toDto() } }

}