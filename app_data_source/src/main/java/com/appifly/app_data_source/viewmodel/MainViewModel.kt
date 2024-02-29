package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.appifly.app_data_source.worker.DataLoadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
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
        val workRequest =
            OneTimeWorkRequestBuilder<DataLoadWorker>().build()
        workManager.enqueue(workRequest)
    }
}