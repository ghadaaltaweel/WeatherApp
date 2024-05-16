package com.syarah.test.core.model.currentWeather.forecastWeather

data class Forecast(
    val date: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val iconId: Int,
    val icon: String
)
