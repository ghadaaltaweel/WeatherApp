package com.syarah.test.core.dao

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.api.model.currentWeather.WeatherRemote
import com.syarah.test.data.cache.entity.CurrentWeatherEntity

interface CurrentWeatherDao {
    suspend fun insert(model: CurrentWeatherEntity):ResultData<Unit>
    suspend fun getCurrentWeather():
            ResultData<CurrentWeatherEntity>
}