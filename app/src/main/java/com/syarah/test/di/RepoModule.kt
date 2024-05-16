package com.syarah.test.di

import com.syarah.test.core.DataCache
import com.syarah.test.core.ErrorHandler
import com.syarah.test.data.ErrorHandlerImp
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import com.syarah.test.data.api.ApiClient
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastRemote
import com.syarah.test.data.cache.Mapper
import com.syarah.test.core.dao.CurrentWeatherDao
import com.syarah.test.core.dao.ForecastDao
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import com.syarah.test.data.repoImp.currentWeather.CurrentWeatherRepoImp
import com.syarah.test.data.repoImp.forecastWeather.ForecastWeatherRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideErrorHandler(): ErrorHandler = ErrorHandlerImp()


    @Singleton
    @Provides
    fun provideUserCurrentWeatherRepo(
        apiClient: ApiClient,
        errorHandler: ErrorHandler,
        remoteMapper: Mapper<CurrentWeatherRemote, CurrentWeather>,
        mapper: Mapper<CurrentWeather, CurrentWeatherEntity>,
        dao: CurrentWeatherDao,
        dataCache: DataCache

        ): CurrentWeatherRepo =
        CurrentWeatherRepoImp(apiClient, errorHandler, remoteMapper = remoteMapper, dao, mapper,dataCache)


    @Singleton
    @Provides
    fun provideUserForecastWeatherRepo(
        apiClient: ApiClient,
        errorHandler: ErrorHandler,
        forecastListRemoteMapper: Mapper<ForecastRemote, Forecast>,
        forecastCityMapper: Mapper<ForecastCity, ForecastCityEntity>,
        forecastListMapper: Mapper<Forecast, ForecastEntity>,
        forecastDao: ForecastDao,
        dataCache: DataCache
        ): ForeCastWeatherRepo =
        ForecastWeatherRepoImp(
            apiClient = apiClient,
            errorHandler = errorHandler,
            forecastListRemoteMapper = forecastListRemoteMapper,
            forecastCityMapper = forecastCityMapper,
            forecastDao = forecastDao,
            forecastListMapper = forecastListMapper,
            dataCache = dataCache
        )
}