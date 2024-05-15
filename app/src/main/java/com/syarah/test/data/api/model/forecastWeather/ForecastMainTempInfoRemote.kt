package com.syarah.test.data.api.model.forecastWeather

import com.google.gson.annotations.SerializedName

data class ForecastMainTempInfoRemote(

    @SerializedName("temp_min")
    val minTemp:Double,

    @SerializedName("temp_max")
    val maxTemp:Double,


)
