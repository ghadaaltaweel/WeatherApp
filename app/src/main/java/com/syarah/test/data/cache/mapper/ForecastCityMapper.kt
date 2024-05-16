package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.ForecastCityEntity

class ForecastCityMapper : Mapper<ForecastCity, ForecastCityEntity> {
    override suspend fun mapTo(input: ForecastCity?): ForecastCityEntity {
        return input.run {
            ForecastCityEntity(
                cityId = input?.cityId ?: -1,
                cityName = input?.cityName ?: "N/A",
                cityCountry = input?.cityCountry ?: "N/A"

            )
        }
    }

    override suspend fun mapFrom(singleInput: ForecastCityEntity): ForecastCity {
        return singleInput.run {
            ForecastCity(
                cityId = singleInput.cityId,
                cityName = singleInput.cityName,
                cityCountry = singleInput.cityCountry
            )
        }
    }
}