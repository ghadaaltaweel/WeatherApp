package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.ForecastEntity

class ForecastListMapper : Mapper<Forecast, ForecastEntity> {
    override suspend fun mapTo(input: Forecast?): ForecastEntity {
        return input.run {
            ForecastEntity(
                date = input?.date ?: -1,
                minTemp = input?.minTemp ?: 0.0,
                maxTemp = input?.maxTemp ?: 0.0,
                iconId = input?.iconId ?: -1,
                icon = input?.icon ?: ""


            )
        }
    }

    override suspend fun mapFrom(singleInput: ForecastEntity): Forecast {
        return singleInput.run {
            Forecast(
                date = singleInput.date,
                minTemp = singleInput.minTemp,
                maxTemp = singleInput.maxTemp,
                iconId = singleInput.iconId,
                icon = singleInput.icon
            )
        }
    }
}