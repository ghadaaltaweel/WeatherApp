package com.syarah.test.data

import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ResultData
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.data.cache.dao.CurrentWeatherDao
import com.syarah.test.data.cache.entity.CurrentWeatherEntity

class CurrentWeatherDaoImp: CurrentWeatherDao {
    override suspend fun insert(model: CurrentWeatherEntity): ResultData<Unit> {
         return ResultData.Success(Unit)
    }

    override suspend fun getCurrentWeather(): ResultData<CurrentWeather> {
        return ResultData.Success(CurrentWeather(-1.0,-1,"","",""))
    }
}