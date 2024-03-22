package com.appifly.tvchannel.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.appifly.tvchannel.app.TvApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TvApplication {
        return app as TvApplication
    }
    @Provides
    fun provideLifecycleOwner(application: Application): LifecycleOwner {
        return application as LifecycleOwner
    }

}
