package com.syarah.test.di

import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import com.syarah.test.core.usecase.GetCurrentWeatherUseCase
import com.syarah.test.core.usecase.GetCurrentWeatherUseCaseImp
import com.syarah.test.core.usecase.GetForecastWeatherUseCase
import com.syarah.test.core.usecase.GetForecastWeatherUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideCurrentWeatherUseCase(repo: CurrentWeatherRepo): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCaseImp(repo)


    @Singleton
    @Provides
    fun provideForecastWeatherUseCase(repo: ForeCastWeatherRepo): GetForecastWeatherUseCase =
        GetForecastWeatherUseCaseImp(repo = repo)
}