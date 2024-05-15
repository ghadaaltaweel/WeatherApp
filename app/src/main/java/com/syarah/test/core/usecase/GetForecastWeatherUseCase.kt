package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather

interface GetForecastWeatherUseCase {
    suspend operator fun invoke(lat:Double,long:Double): ResultData<ForeCastWeather>
}