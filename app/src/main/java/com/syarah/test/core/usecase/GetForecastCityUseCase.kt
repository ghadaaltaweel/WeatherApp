package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity

interface GetForecastCityUseCase {
    suspend operator fun invoke(): ResultData<ForecastCity>

}