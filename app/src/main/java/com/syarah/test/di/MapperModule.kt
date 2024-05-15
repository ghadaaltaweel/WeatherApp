package com.syarah.test.di

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import com.syarah.test.data.cache.mapper.CurrentWeatherMapper
import com.syarah.test.data.cache.mapper.CurrentWeatherRemoteMapper
import com.syarah.test.data.cache.mapper.ForecastRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Singleton
    @Provides
    fun provideWeatherRemoteMapper(): Mapper<CurrentWeatherRemote, CurrentWeather> {
        return CurrentWeatherRemoteMapper(
        )
    }
    @Singleton
    @Provides
    fun provideForecastRemoteMapper(): Mapper<ForecastWeatherRemoteResponse, ForeCastWeather> {
        return ForecastRemoteMapper(
        )
    }

    @Singleton
    @Provides
    fun provideWeatherMapper(): Mapper<CurrentWeather, CurrentWeatherEntity> {
        return CurrentWeatherMapper(
        )
    }
}