package com.syarah.test.data.cache.mapper

import com.google.gson.annotations.SerializedName
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.data.api.model.forecastWeather.ForecastMainTempInfoRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemote
import com.syarah.test.data.cache.Mapper

class ForecastListRemoteMapper : Mapper<ForecastRemote, Forecast> {
    override suspend fun mapTo(input: ForecastRemote?): Forecast {
        return input.run {
            Forecast(
                date = input?.date ?: -1,
                minTemp = input?.mainInfo?.minTemp ?: 0.0,
                maxTemp = input?.mainInfo?.maxTemp ?: 0.0,
                iconId = input?.weather?.first()?.id ?: -1,
                icon = input?.weather?.first()?.icon ?: ""
            )

        }
    }

    override suspend fun mapFrom(singleInput: Forecast): ForecastRemote {
        return singleInput.run {
            ForecastRemote(
                date = singleInput.date,
                mainInfo = ForecastMainTempInfoRemote(0.0, 0.0),
                weather = emptyList()

            )
        }
    }
}