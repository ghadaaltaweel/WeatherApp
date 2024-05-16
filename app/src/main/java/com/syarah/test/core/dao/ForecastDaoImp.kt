package com.syarah.test.core.dao

import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ResultData
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity
import io.objectbox.Box
import timber.log.Timber
import javax.inject.Inject

class ForecastDaoImp @Inject constructor(
    private val forecastBox: Box<ForecastEntity>?,
    private val forecastCityBox: Box<ForecastCityEntity>?

) : ForecastDao {
    override suspend fun insert(model: ForecastCityEntity) = forecastCityBox?.run {
        try {
            put(model)
            Timber.d("success cashing Forecast City")
            ResultData.Success(Unit)

        } catch (e: Exception) {
            Timber.d("fail cashing Forecast City")
            ResultData.Error(ErrorEntity.InternalError("error : ${e.message}"))

        }
    } ?: run()
    {
        Timber.d("error cashing Forecast City")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))

    }

    override suspend fun insert(model: ForecastEntity) = forecastBox?.run {
        try {
            put(model)
            Timber.d("success cashing Forecast ")
            ResultData.Success(Unit)

        } catch (e: Exception) {
            Timber.d("fail cashing Forecast ")
            ResultData.Error(ErrorEntity.InternalError("error : ${e.message}"))

        }
    } ?: run()
    {
        Timber.d("error cashing Forecast ")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))

    }

    override suspend fun getForecastCity() = forecastCityBox?.run {

        val query =
            query().build()
        val search = query.find()
        query?.close()
        Timber.d("success get cashed  forecast city")
        ResultData.Success(search[0])


    } ?: run()
    {
        Timber.d("error get cashed forecast city")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))

    }

    override suspend fun getForecast()= forecastBox?.run {

        val query =
            query().build()
        val search = query.find()
        query?.close()
        Timber.d("success get cashed  forecast city")
        ResultData.Success(search[0])

    } ?: run()
    {
        Timber.d("error get cashed forecast city")
        ResultData.Error(ErrorEntity.InternalError("CurrentWeather instance Error"))

    }
}