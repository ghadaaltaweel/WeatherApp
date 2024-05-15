package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import javax.inject.Inject

class GetForecastWeatherUseCaseImp @Inject constructor(
    private val repo:ForeCastWeatherRepo
):GetForecastWeatherUseCase{
    override suspend fun invoke(lat: Double, long: Double): ResultData<ForeCastWeather> {
       return repo.getForecastWeather(lat,long)
    }

}