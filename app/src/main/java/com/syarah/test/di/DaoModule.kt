package com.syarah.test.di

import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import com.syarah.test.core.usecase.GetCurrentWeatherUseCase
import com.syarah.test.core.usecase.GetCurrentWeatherUseCaseImp
import com.syarah.test.data.CurrentWeatherDaoImp
import com.syarah.test.data.cache.dao.CurrentWeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun provideCurrentWeatherDao(): CurrentWeatherDao =
        CurrentWeatherDaoImp()

}