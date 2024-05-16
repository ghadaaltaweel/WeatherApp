package com.syarah.test.di

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import com.syarah.test.data.cache.mapper.CurrentWeatherMapper
import com.syarah.test.data.cache.mapper.CurrentWeatherRemoteMapper
import com.syarah.test.data.cache.mapper.ForecastCityMapper
import com.syarah.test.data.cache.mapper.ForecastListMapper
import com.syarah.test.data.cache.mapper.ForecastListRemoteMapper
import com.syarah.test.data.cache.mapper.ForecastResponseRemoteMapper
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
    fun provideForecastRemoteMapper(): Mapper<ForecastWeatherRemoteResponse, ForecastCity> {
        return ForecastResponseRemoteMapper(
        )
    }

    @Singleton
    @Provides
    fun provideForecastMapper(): Mapper<Forecast, ForecastEntity> {
        return ForecastListMapper(
        )
    }
    @Singleton
    @Provides
    fun provideForecastCityMapper(): Mapper<ForecastCity, ForecastCityEntity> {
        return ForecastCityMapper(
        )
    }
    @Singleton
    @Provides
    fun provideForecastListMapper(): Mapper<ForecastRemote, Forecast> {
        return ForecastListRemoteMapper(
        )
    }

    @Singleton
    @Provides
    fun provideWeatherMapper(): Mapper<CurrentWeather, CurrentWeatherEntity> {
        return CurrentWeatherMapper(
        )
    }
}