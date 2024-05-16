package com.syarah.test.core.dao

import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ResultData
import com.syarah.test.data.cache.entity.CurrentWeatherEntity
import io.objectbox.Box
import timber.log.Timber
import javax.inject.Inject

class CurrentWeatherDaoImp @Inject constructor(
    private val currentWeatherBox: Box<CurrentWeatherEntity>?
) : CurrentWeatherDao {
    override suspend fun insert(model: CurrentWeatherEntity) = currentWeatherBox?.run {
        try {
            put(model)
            Timber.d("success cashing current weather")
            ResultData.Success(Unit)

        } catch (e: Exception) {
            Timber.d("fail cashing current weather")
            ResultData.Error(ErrorEntity.InternalError("error : ${e.message}"))

        }
    } ?: run()
    {
        Timber.d("error cashing Current Weather")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))
    }

    override suspend fun getCurrentWeather() = currentWeatherBox?.run {

        val query =
            query().build()
        val search = query.find()
        query?.close()
        Timber.d("success get cashed current weather")
        ResultData.Success(search[0])

    } ?: run() {
        Timber.d("error get cashed current weather")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))

    }
}