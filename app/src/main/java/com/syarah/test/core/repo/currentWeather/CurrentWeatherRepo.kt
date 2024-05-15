package com.syarah.test.core.repo.currentWeather

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.CurrentWeather

interface CurrentWeatherRepo {
    suspend fun getCurrentWeatherRemotely(lat:Double, long:Double):ResultData<CurrentWeather>
    suspend fun getCurrentWeatherLocally(lat:Double, long:Double):ResultData<CurrentWeather>
    suspend fun insertCurrentWeatherToCache(model:CurrentWeather):ResultData<Unit>
}