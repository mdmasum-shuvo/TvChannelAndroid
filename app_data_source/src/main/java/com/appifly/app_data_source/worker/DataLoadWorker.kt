package com.appifly.app_data_source.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.BannerListApiUseCase
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.app_data_source.data.TvShowListApiUseCase
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltWorker
class DataLoadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    @Assisted val categoryListUseCase: CategoryListUseCase,
    @Assisted val channelListUseCase: ChannelListUseCase,
    @Assisted val tvShowListApiUseCase: TvShowListApiUseCase,
    @Assisted val bannerListApiUseCase: BannerListApiUseCase,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

        categoryListUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))
        channelListUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        bannerListApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        tvShowListApiUseCase.invoke().onEach {

        }.launchIn(CoroutineScope(Dispatchers.IO))

        Log.e("work", "work manager start to work")
        return Result.success()
    }
}