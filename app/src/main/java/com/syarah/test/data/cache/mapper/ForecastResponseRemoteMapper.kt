package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.data.api.model.forecastWeather.CityRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.cache.Mapper

class ForecastResponseRemoteMapper : Mapper<ForecastWeatherRemoteResponse, ForecastCity> {
    override suspend fun mapTo(input: ForecastWeatherRemoteResponse?): ForecastCity {
        return input.run {
            ForecastCity(
                cityId = input?.city?.id ?: -1,
                cityName = input?.city?.name ?: "N/A",
                cityCountry = input?.city?.country ?: "N/A"
            )
        }

    }

    override suspend fun mapFrom(singleInput: ForecastCity): ForecastWeatherRemoteResponse {
        return singleInput.run {
            ForecastWeatherRemoteResponse(
                forecastList = emptyList(),
                city = CityRemote(
                    -1,
                    "",
                    ""
                )
            )
        }
    }
}