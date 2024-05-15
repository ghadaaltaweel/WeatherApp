package com.syarah.test.data.api

import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
//    @GET("https://api.openweathermap.org/data/2.5/weather?lat=30.585163&lon=36.238415&appid=c45719be9dea179c84566fc2b4e02db4")
    @GET("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={long}&appid=c45719be9dea179c84566fc2b4e02db4")
    suspend fun getCurrentWeather(
        @Path("lat") lat: Double,
        @Path("long") long: Double,
    ): Response<CurrentWeatherRemote>

    //    @GET("api.openweathermap.org/data/2.5/forecast?lat=30.585163&lon=36.238415&appid=c45719be9dea179c84566fc2b4e02db4")
    @GET("api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={long}&appid=c45719be9dea179c84566fc2b4e02db4")
    suspend fun getForecastWeather(
        @Path("lat") lat: Double,
        @Path("long") long: Double,
    ): Response<ForecastWeatherRemoteResponse>
}