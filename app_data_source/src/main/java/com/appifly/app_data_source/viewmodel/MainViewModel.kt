package com.appifly.app_data_source.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.appifly.app_data_source.worker.DataLoadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val lifecycleOwner: LifecycleOwner
) : ViewModel() {

    init {
        //getCategoryData()
        applyWorker()
    }

    private fun applyWorker() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = PeriodicWorkRequestBuilder<DataLoadWorker>(
            1, TimeUnit.DAYS, // repeatInterval (the period cycle)
            1, TimeUnit.DAYS
        ).setConstraints(constraints) // flexInterval
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdLiveData(workRequest.id)
            .observeForever { workInfo ->
                when (workInfo.state) {
                    WorkInfo.State.RUNNING -> {
                        // Work is running
                        Log.e("Worker","Work manage is running")
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        Log.e("Worker","Work manage is Success")

                        // Work completed successfully
                    }

                    WorkInfo.State.FAILED -> {
                        Log.e("Worker","Work manage is Failed")

                        // Work failed
                    }

                    WorkInfo.State.CANCELLED -> {
                        Log.e("Worker","Work manage is Canceled")

                        // Work was cancelled
                    }

                    else -> {
                        // Handle other states
                    }
                }
            }
    }
}