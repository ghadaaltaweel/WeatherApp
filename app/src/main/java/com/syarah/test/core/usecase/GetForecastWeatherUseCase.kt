package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity

interface GetForecastWeatherUseCase {
    suspend operator fun invoke(lat:Double,long:Double): ResultData<List<Forecast>>
}