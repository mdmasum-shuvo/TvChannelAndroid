package com.appifly.app_data_source.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.AdIdApiUseCase
import com.appifly.app_data_source.data.BannerListApiUseCase
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.app_data_source.data.EventApiUseCase
import com.appifly.app_data_source.data.TvShowListApiUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@HiltWorker
class DataLoadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    @Assisted val categoryListUseCase: CategoryListUseCase,
    @Assisted val channelListUseCase: ChannelListUseCase,
    @Assisted val tvShowListApiUseCase: TvShowListApiUseCase,
    @Assisted val bannerListApiUseCase: BannerListApiUseCase,
    @Assisted val adIdApiUseCase: AdIdApiUseCase,
    @Assisted val eventApiUseCase: EventApiUseCase,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

        adIdApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        categoryListUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        eventApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        channelListUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        bannerListApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        tvShowListApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))
        return Result.success()
    }
}