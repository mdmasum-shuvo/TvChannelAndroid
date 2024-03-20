package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.appifly.app_data_source.worker.DataLoadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    init {
        //getCategoryData()
        applyWorker()
    }

    private fun applyWorker() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).setRequiresBatteryNotLow(true)
            .build()
        val workRequest = PeriodicWorkRequestBuilder<DataLoadWorker>(
            1, TimeUnit.DAYS, // repeatInterval (the period cycle)
            1, TimeUnit.DAYS
        ).setConstraints(constraints) // flexInterval
            .build()

        workManager.enqueue(workRequest)
    }
}