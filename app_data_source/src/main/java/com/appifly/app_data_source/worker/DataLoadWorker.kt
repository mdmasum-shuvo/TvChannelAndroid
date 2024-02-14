package com.appifly.app_data_source.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.appifly.app_data_source.data.CategoryListUseCase
import com.appifly.network.remote_data.repository.NetworkDataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltWorker
class DataLoadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    ) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

      //  repository.invoke()

        Log.e("work","work manager start to work")
        return Result.success()
    }
}