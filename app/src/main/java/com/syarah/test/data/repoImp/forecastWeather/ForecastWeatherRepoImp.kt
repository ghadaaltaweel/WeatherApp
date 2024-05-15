package com.syarah.test.data.repoImp.forecastWeather

import com.syarah.test.core.ErrorHandler
import com.syarah.test.core.ResultData
import com.syarah.test.core.doIfSuccess
import com.syarah.test.core.model.currentWeather.forecastWeather.ForeCastWeather
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import com.syarah.test.data.api.ApiClient
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.api.validate
import com.syarah.test.data.cache.Mapper
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class ForecastWeatherRepoImp @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHandler: ErrorHandler,
    private val remoteMapper: Mapper<ForecastWeatherRemoteResponse, ForeCastWeather>
) : ForeCastWeatherRepo {
    override suspend fun getForecastWeather(lat: Double, long: Double): ResultData<ForeCastWeather> {
        return coroutineScope {
            try {
                apiClient.getForecastWeather(lat,long).validate().doIfSuccess { forecastRemoteResponse ->
                    Timber.d("success get forecast Weather")
                    remoteMapper.mapTo(forecastRemoteResponse)

                }
            } catch (e: Exception) {
                Timber.d("error get remote Current Weather where ${e.message}")

                ResultData.Error(errorHandler.getError(e))
            }
        }
    }
}