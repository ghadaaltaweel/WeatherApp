package com.syarah.test.data.api.model.currentWeather

import com.google.gson.annotations.SerializedName

data class MainDataWeatherRemote(
    @SerializedName("temp")
    val temp: Double

)
