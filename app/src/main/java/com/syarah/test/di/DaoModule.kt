package com.syarah.test.di

import com.syarah.test.core.dao.CurrentWeatherDaoImp
import com.syarah.test.core.dao.CurrentWeatherDao
import com.syarah.test.core.dao.ForecastDao
import com.syarah.test.core.dao.ForecastDaoImp
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun provideCurrentWeatherDao(
        currentWeatherBox: Box<CurrentWeatherEntity>?
    ): CurrentWeatherDao =
        CurrentWeatherDaoImp(
            currentWeatherBox
        )

    @Singleton
    @Provides
    fun provideForecastDao(
        forecastBox: Box<ForecastEntity>?,
        forecastCityBox: Box<ForecastCityEntity>?

    ): ForecastDao =
        ForecastDaoImp(
            forecastBox = forecastBox,
            forecastCityBox = forecastCityBox

        )
}