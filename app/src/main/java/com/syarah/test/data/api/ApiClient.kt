package com.syarah.test.data.api

import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid:String
    ): Response<CurrentWeatherRemote>

    @GET("data/2.5/forecast?")
    suspend fun getForecastWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid:String
    ): Response<ForecastWeatherRemoteResponse>
}