package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.forecastWeather.City
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.cache.Mapper

class ForecastRemoteMapper: Mapper<ForecastWeatherRemoteResponse, ForeCastWeather> {
    override suspend fun mapTo(input: ForecastWeatherRemoteResponse?): ForeCastWeather {
       return ForeCastWeather("",emptyList<Forecast>(), City(-1,"",""))
    }
}