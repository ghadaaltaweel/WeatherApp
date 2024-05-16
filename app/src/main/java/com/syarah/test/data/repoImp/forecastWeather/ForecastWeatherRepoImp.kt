package com.syarah.test.data.repoImp.forecastWeather

import com.syarah.test.core.DataCache
import com.syarah.test.core.ErrorHandler
import com.syarah.test.core.ResultData
import com.syarah.test.core.dao.ForecastDao
import com.syarah.test.core.doIfSuccess
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.repo.forecastWeather.ForeCastWeatherRepo
import com.syarah.test.data.api.ApiClient
import com.syarah.test.data.api.model.forecastWeather.ForecastRemote
import com.syarah.test.data.api.model.forecastWeather.ForecastWeatherRemoteResponse
import com.syarah.test.data.api.validate
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class ForecastWeatherRepoImp @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHandler: ErrorHandler,
    private val forecastListRemoteMapper: Mapper<ForecastRemote, Forecast>,
    private val forecastListMapper: Mapper<Forecast, ForecastEntity>,
    private val forecastCityMapper: Mapper<ForecastCity, ForecastCityEntity>,
    private val forecastDao: ForecastDao,
    private val dataCache: DataCache
) : ForeCastWeatherRepo {
    override suspend fun getForecastWeatherRemotely(
        lat: Double,
        long: Double
    ): ResultData<List<Forecast>> {
        return coroutineScope {
            try {
                apiClient.getForecastWeather(
                    lat.toString(),
                    long.toString(),
                    dataCache.getToken()
//                    "c45719be9dea179c84566fc2b4e02db4"
                )
                    .validate()
                    .doIfSuccess { forecastRemoteResponse ->
                        Timber.d("success get forecast Weather")
//                        remoteMapper.mapTo(forecastRemoteResponse)


                        insertForecastCityToCache(ForecastCity(
                            cityId = forecastRemoteResponse.city?.id?:-1,
                            cityName = forecastRemoteResponse.city?.name?:"N/A",
                            cityCountry = forecastRemoteResponse.city?.country?:"N/A"
                        ))
                        forecastRemoteResponse.forecastList?.map {
                            forecastListRemoteMapper.mapTo(it)
                        } ?: emptyList()

                    }
            } catch (e: Exception) {
                Timber.d("error get remote Current Weather where ${e.message}")

                ResultData.Error(errorHandler.getError(e))
            }
        }
    }

    override suspend fun getForecastLocally(): ResultData<Forecast> {
        return coroutineScope {
            forecastDao.getForecast().doIfSuccess {
                forecastListMapper.mapFrom(it)
            }
        }
    }

    override suspend fun insertForecastToCache(model: Forecast): ResultData<Unit> {
        return coroutineScope {
            forecastDao.insert(forecastListMapper.mapTo(model))
        }
    }

    override suspend fun getForecastCityLocally(): ResultData<ForecastCity> {
        return coroutineScope {
            forecastDao.getForecastCity().doIfSuccess {
                forecastCityMapper.mapFrom(it)
            }
        }
    }

     private suspend fun insertForecastCityToCache(model: ForecastCity): ResultData<Unit> {
        return coroutineScope {
            forecastDao.insert(forecastCityMapper.mapTo(model))
        }
    }


}