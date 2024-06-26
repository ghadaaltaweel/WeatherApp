package com.syarah.test.data.cache.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ForecastEntity(
    @Id
    var id: Long = 0,
    val date: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val iconId: Int,
    val icon: String
)
