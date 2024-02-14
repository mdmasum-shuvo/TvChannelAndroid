package com.appifly.app_data_source.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DataLoadWorker @AssistedInject constructor(
    private val repository: CategoryListUseCase,
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters
) : Worker(appContext, params) {
    override fun doWork(): Result {
        repository.invoke()
        Toast.makeText(applicationContext, "start worker", Toast.LENGTH_SHORT).show()
        Log.e("work","work manager start to work")
        return Result.success()
    }
}