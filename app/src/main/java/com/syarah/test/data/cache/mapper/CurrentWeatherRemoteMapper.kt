package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.cache.Mapper
import javax.inject.Inject

class CurrentWeatherRemoteMapper @Inject constructor(

): Mapper<CurrentWeatherRemote, CurrentWeather> {
    override suspend fun mapTo(input: CurrentWeatherRemote?): CurrentWeather {
        return input.run {
            CurrentWeather(
                temp = input?.main?.temp?:0.0,
                weatherId = input?.weather?.id?:-1,
                main= input?.weather?.main?:"",
                icon = input?.weather?.icon?:"",
                description = input?.weather?.description?:""
            )
        }
    }



}