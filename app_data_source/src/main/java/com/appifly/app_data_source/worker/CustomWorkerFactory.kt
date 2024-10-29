package com.appifly.app_data_source.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import javax.inject.Inject


class CustomWorkerFactory @Inject constructor(
    private val categoryListUseCase: CategoryListUseCase,
    private val channelListUseCase: ChannelListUseCase

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context, workerClassName: String, workerParameters: WorkerParameters
    ): ListenableWorker = DataLoadWorker(
        appContext,
        workerParameters,
        categoryListUseCase,
        channelListUseCase

    )

}