package com.appifly.network


import android.content.Context
import com.appifly.network.remote_data.NetworkCallbackApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.Interceptor.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val AUTHORIZATION = "Authorization"
const val TOKEN_KEY = "token_key"
const val DEFAULT_VALUE = ""


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"


    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(Cache(File(appContext.cacheDir, "http-cache"), 20L * 1024L * 1024L)) // 20 MiB
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                chain.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun provideNetworkCallback(okHttpClient: OkHttpClient, moshi: Moshi, baseUrl: String) =
        createRetrofitWithMoshi<NetworkCallbackApi>(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )

}