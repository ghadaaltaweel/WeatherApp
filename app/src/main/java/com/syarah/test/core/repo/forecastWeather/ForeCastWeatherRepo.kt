package com.syarah.test.core.repo.forecastWeather

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather

interface ForeCastWeatherRepo {
    suspend fun getForecastWeather(lat: Double, long: Double): ResultData<ForeCastWeather>

}