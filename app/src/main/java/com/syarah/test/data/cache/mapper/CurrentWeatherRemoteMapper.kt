package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.model.currentWeather.MainDataWeatherRemote
import com.syarah.test.data.cache.Mapper
import javax.inject.Inject

class CurrentWeatherRemoteMapper @Inject constructor(

): Mapper<CurrentWeatherRemote, CurrentWeather> {
    override suspend fun mapTo(input: CurrentWeatherRemote?): CurrentWeather {
        return input.run {
            CurrentWeather(
                temp = input?.main?.temp?:0.0,
                weatherId = input?.weather?.first()?.id?:-1,
                main= input?.weather?.first()?.main?:"",
                icon = input?.weather?.first()?.icon?:"",
                description = input?.weather?.first()?.description?:"",
                countryName = input?.name?:"N/A"
            )
        }
    }

    override suspend fun mapFrom(singleInput: CurrentWeather): CurrentWeatherRemote {
        return singleInput.run {
            CurrentWeatherRemote(
                weather = emptyList(),
                main = MainDataWeatherRemote(
                    0.0
                ),
                name = "N/A"

            )
        }
    }


}