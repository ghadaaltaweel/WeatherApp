package com.syarah.test.di

import com.syarah.test.core.ErrorHandler
import com.syarah.test.data.ErrorHandlerImp
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather
import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import com.syarah.test.data.api.ApiClient
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.dao.CurrentWeatherDao
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
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

        ): CurrentWeatherRepo =
        CurrentWeatherRepoImp(apiClient, errorHandler, remoteMapper = remoteMapper, dao, mapper)


    @Singleton
    @Provides
    fun provideUserForecastWeatherRepo(
        apiClient: ApiClient,
        errorHandler: ErrorHandler,
        remoteMapper: Mapper<ForecastWeatherRemoteResponse, ForeCastWeather>
    ): ForeCastWeatherRepo =
        ForecastWeatherRepoImp(apiClient, errorHandler, remoteMapper)
}