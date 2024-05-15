package com.syarah.test.data.api.mapper

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote

fun CurrentWeatherRemote.toDomain():CurrentWeather{
    return CurrentWeather(
        temp = main.temp,
        weatherId = weather.id,
        icon = weather.icon,
        description = weather.description,
        main = weather.main

    )
}