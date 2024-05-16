package com.syarah.test.data.api.model.currentWeather

import com.google.gson.annotations.SerializedName

data class CurrentWeatherRemote(
    @SerializedName("weather")
    val weather:List<WeatherRemote>?,

    @SerializedName("main")
    val main:MainDataWeatherRemote?,

    @SerializedName("name")
    val name:String?
)
