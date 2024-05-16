package com.syarah.test.core.repo.forecastWeather

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity

interface ForeCastWeatherRepo {
    suspend fun getForecastWeatherRemotely(lat: Double, long: Double): ResultData<List<Forecast>>
    suspend fun getForecastLocally(): ResultData<Forecast>
    suspend fun insertForecastToCache(model: Forecast): ResultData<Unit>
    suspend fun getForecastCityLocally(): ResultData<ForecastCity>
//    suspend fun insertForecastCityToCache(model: ForecastCity): ResultData<Unit>

}