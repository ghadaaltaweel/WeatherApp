package com.syarah.test.data.api.model.forecastWeather

import com.google.gson.annotations.SerializedName

data class ForecastWeatherRemoteResponse(

    @SerializedName("message")
    val message:String?,

    @SerializedName("list")
    val forecastList:List<ForecastRemote>?,

    @SerializedName("city")
    val city:CityRemote?
)
