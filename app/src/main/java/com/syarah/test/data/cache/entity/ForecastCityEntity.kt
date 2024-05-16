package com.syarah.test.data.cache.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ForecastCityEntity(
    @Id
    var id: Long = 0,
    val cityId: Int,
    val cityName: String,
    val cityCountry: String
)
