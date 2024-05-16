package com.syarah.test.di

import android.content.Context
import android.content.SharedPreferences
import com.syarah.test.core.DataCache
import com.syarah.test.data.sharedPreference.DataCacheImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences("WEATHER_PREF", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideUserDataCache(sharedPreferences: SharedPreferences): DataCache {
        return DataCacheImp(sharedPreferences)
    }
}