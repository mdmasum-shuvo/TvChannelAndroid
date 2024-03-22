package com.appifly.tvchannel.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.work.Configuration
import com.appifly.app_data_source.worker.CustomWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TvApplication: Application(), Configuration.Provider , LifecycleOwner {
    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    private val lifecycleRegistry = LifecycleRegistry(this)

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()
        // Initialize your application
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }


    override val lifecycle: Lifecycle
        get() = lifecycleRegistry


}
