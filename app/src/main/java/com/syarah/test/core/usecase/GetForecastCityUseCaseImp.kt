package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import javax.inject.Inject

class GetForecastCityUseCaseImp @Inject constructor(
    private val repo: ForeCastWeatherRepo
) : GetForecastCityUseCase {
    override suspend fun invoke(): ResultData<ForecastCity> {
        return repo.getForecastCityLocally()
    }
}