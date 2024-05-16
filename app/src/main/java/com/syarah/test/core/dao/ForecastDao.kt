package com.syarah.test.core.dao

import com.syarah.test.core.ResultData
import com.syarah.test.data.cache.entity.ForecastCityEntity
import com.syarah.test.data.cache.entity.ForecastEntity

interface ForecastDao {
    suspend fun insert(model: ForecastCityEntity): ResultData<Unit>
    suspend fun getForecastCity():
            ResultData<ForecastCityEntity>

    suspend fun insert(model: ForecastEntity): ResultData<Unit>
    suspend fun getForecast():
            ResultData<ForecastEntity>

}