package com.appifly.app_data_source.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.AdIdApiUseCase
import com.appifly.app_data_source.data.BannerListApiUseCase
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.app_data_source.data.TvShowListApiUseCase
import javax.inject.Inject


class CustomWorkerFactory @Inject constructor(
    private val categoryListUseCase: CategoryListUseCase,
    private val channelListUseCase: ChannelListUseCase,
    private val bannerListApiUseCase: BannerListApiUseCase,
    private val tvShowListApiUseCase: TvShowListApiUseCase,
    private val adIdApiUseCase: AdIdApiUseCase,

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context, workerClassName: String, workerParameters: WorkerParameters
    ): ListenableWorker = DataLoadWorker(
        appContext,
        workerParameters,
        categoryListUseCase,
        channelListUseCase,
        tvShowListApiUseCase,
        bannerListApiUseCase,
        adIdApiUseCase

    )

}