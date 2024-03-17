package com.appifly.tvchannel.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.work.Configuration
import com.appifly.app_data_source.worker.CustomWorkerFactory
import com.appifly.tvchannel.R
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TvApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            applicationContext.getString(R.string.notification_channel_id),
            applicationContext.getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

    }
    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

}
