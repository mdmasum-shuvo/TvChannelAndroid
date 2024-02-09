package com.appifly.network.remote_data

import com.appifly.network.createRetrofitWithMoshi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkCallback(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        baseUrl: String
    ) =
        createRetrofitWithMoshi<NetworkCallbackApi>(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
}