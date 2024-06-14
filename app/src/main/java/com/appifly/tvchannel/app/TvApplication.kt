package com.appifly.tvchannel.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.work.Configuration
import com.appifly.app_data_source.worker.CustomWorkerFactory
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
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
        // FirebaseMessaging.getInstance().subscribeToTopic("mastermind_notification");
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().subscribeToTopic("all")

    }


    override val lifecycle: Lifecycle
        get() = lifecycleRegistry


}
