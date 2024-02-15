package com.appifly.tvchannel.app

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.app_data_source.data.ChannelListUseCase
import com.appifly.app_data_source.data.NetworkDataRepositoryImpl
import com.appifly.app_data_source.worker.CustomWorkerFactory
import com.appifly.app_data_source.worker.DataLoadWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TvApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

}
