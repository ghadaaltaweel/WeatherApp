package com.syarah.test.di

import android.content.Context
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import com.syarah.test.data.cache.entity.MyObjectBox
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
class BoxStoreModule {



    @Singleton
    @Provides
    fun provideObjectBoxStore(@ApplicationContext context: Context): BoxStore {
        return MyObjectBox.builder()
            .androidContext(context)
            .build()
    }


    @Singleton
    @Provides
    fun provideCurrentWeather(boxStore: BoxStore): Box<CurrentWeatherEntity>? {
        return boxStore.boxFor(CurrentWeatherEntity::class.java)
    }

    @Singleton
    @Provides
    fun provideForecastWeather(boxStore: BoxStore): Box<ForecastEntity>? {
        return boxStore.boxFor(ForecastEntity::class.java)
    }

    @Singleton
    @Provides
    fun provideForecastCity(boxStore: BoxStore): Box<ForecastCityEntity>? {
        return boxStore.boxFor(ForecastCityEntity::class.java)
    }

}