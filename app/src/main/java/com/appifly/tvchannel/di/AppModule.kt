package com.appifly.tvchannel.di

import android.content.Context
import com.appifly.tvchannel.app.TvApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val applicationScope = CoroutineScope(Dispatchers.Default)

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TvApplication {
        return app as TvApplication
    }
}