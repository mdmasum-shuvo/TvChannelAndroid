package com.appifly.app_data_source.worker

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerInitializer :Initializer<WorkManager> {
    @Singleton
    @Provides
    override fun create(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
      return emptyList()

    }
}