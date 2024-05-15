package com.syarah.test.core.model.currentWeather.forecastWeather

data class Forecast(
    val date: Long,

    val mainInfo: ForecastMainTempInfo,

    val weather: ForecastIcon
)
