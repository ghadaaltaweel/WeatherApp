package com.syarah.test.data.cache.mapper

import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor(

) : Mapper<CurrentWeather, CurrentWeatherEntity> {
    override suspend fun mapTo(input: CurrentWeather?): CurrentWeatherEntity {
        return input.run {
            CurrentWeatherEntity(
                weatherId = input?.weatherId ?: -1,
                temp = input?.temp ?: 0.0,
                main = input?.main ?: "",
                description = input?.description ?: "",
                icon = input?.icon ?: "",
                countryName = input?.countryName ?: "N/A"
            )
        }
    }

    override suspend fun mapFrom(singleInput: CurrentWeatherEntity): CurrentWeather {
        return singleInput.run {
            CurrentWeather(
                temp = singleInput.temp,
                weatherId = singleInput.weatherId,
                main = singleInput.main,
                description = singleInput.description,
                icon = singleInput.icon,
                countryName = singleInput.countryName
            )
        }
    }

}