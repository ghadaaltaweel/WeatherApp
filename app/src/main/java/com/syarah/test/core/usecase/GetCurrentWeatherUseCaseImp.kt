package com.syarah.test.core.usecase

import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import javax.inject.Inject

class GetCurrentWeatherUseCaseImp @Inject constructor(
    private val repo: CurrentWeatherRepo
) : GetCurrentWeatherUseCase {
    override suspend fun invoke(lat: Double, long: Double): ResultData<CurrentWeather> {
        return repo.getCurrentWeatherRemotely(lat,long)
    }
}