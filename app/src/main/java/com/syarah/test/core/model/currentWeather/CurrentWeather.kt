package com.syarah.test.core.model.currentWeather


data class CurrentWeather(
    val temp: Double,

    val weatherId: Int,

    val main: String,

    val description: String,

    val icon: String
)
