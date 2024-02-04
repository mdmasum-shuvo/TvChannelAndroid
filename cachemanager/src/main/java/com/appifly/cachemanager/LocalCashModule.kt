package com.appifly.cachemanager

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
object LocalCashModule {

    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): TvDatabase {
        return Room.databaseBuilder(context, TvDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }


}