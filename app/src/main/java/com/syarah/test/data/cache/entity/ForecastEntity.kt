package com.syarah.test.data.cache.entity

import com.syarah.test.data.api.model.forecastWeather.ForecastMainTempInfoRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemote
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

//@Entity
data class ForecastEntity(
//    @Id
    val id:Long=0,
    val date: Long,
    val mainInfo: ForecastMainTempInfoRemote,
    val weather: ForecastWeatherRemote
)
