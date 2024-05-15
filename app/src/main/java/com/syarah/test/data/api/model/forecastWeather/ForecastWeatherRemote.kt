package com.syarah.test.data.api.model.forecastWeather

import com.google.gson.annotations.SerializedName

data class ForecastWeatherRemote(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("icon")
    val icon: String

)
