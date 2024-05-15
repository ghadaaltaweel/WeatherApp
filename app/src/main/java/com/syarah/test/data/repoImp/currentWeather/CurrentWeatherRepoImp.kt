package com.syarah.test.data.repoImp.currentWeather

import com.syarah.test.core.ErrorHandler
import com.syarah.test.core.ResultData
import com.syarah.test.core.doIfSuccess
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.repo.currentWeather.CurrentWeatherRepo
import com.syarah.test.data.api.ApiClient
import com.syarah.test.data.api.model.currentWeather.CurrentWeatherRemote
import com.syarah.test.data.api.validate
import com.syarah.test.data.cache.Mapper
import com.syarah.test.data.cache.dao.CurrentWeatherDao
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class CurrentWeatherRepoImp @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHandler: ErrorHandler,
    private val remoteMapper: Mapper<CurrentWeatherRemote, CurrentWeather>,
    private val currentWeatherDao:CurrentWeatherDao,
    private val mapper: Mapper<CurrentWeather, CurrentWeatherEntity>,



    ):CurrentWeatherRepo {
    override suspend fun getCurrentWeatherRemotely(lat: Double, long: Double)
    : ResultData<CurrentWeather>
    {

        return coroutineScope {
            try {
                apiClient.getCurrentWeather(lat,long).validate().doIfSuccess { weatherRemote ->
                    Timber.d("success get remote Current Weather")

                    remoteMapper.mapTo(weatherRemote)


                }

            }catch (e:Exception)
            {
                Timber.d("error get remote Current Weather where ${e.message}")

                ResultData.Error(errorHandler.getError(e))

            }
        }
    }

    override suspend fun getCurrentWeatherLocally(
        lat: Double,
        long: Double
    ): ResultData<CurrentWeather> {
        return coroutineScope {
            currentWeatherDao.getCurrentWeather()
        }
    }

    override suspend fun insertCurrentWeatherToCache(model: CurrentWeather): ResultData<Unit> {
        return coroutineScope {
            currentWeatherDao.insert(mapper.mapTo(model))
        }

    }
}