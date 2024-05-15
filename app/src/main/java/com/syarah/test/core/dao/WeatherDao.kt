package com.syarah.test.core.dao


import com.syarah.test.data.api.model.currentWeather.WeatherRemote
import retrofit2.Response


interface WeatherDao {

    suspend fun insertWeather(): Response<WeatherRemote>
}