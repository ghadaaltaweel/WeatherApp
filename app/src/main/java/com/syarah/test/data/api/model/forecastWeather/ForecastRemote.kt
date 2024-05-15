package com.syarah.test.data.api.model.forecastWeather

import com.google.gson.annotations.SerializedName

data class ForecastRemote(
    @SerializedName("dt")
    val date:Long?,

    @SerializedName("main")
    val mainInfo:ForecastMainTempInfoRemote?,

    @SerializedName("weather")
    val weather:ForecastWeatherRemote?

)
