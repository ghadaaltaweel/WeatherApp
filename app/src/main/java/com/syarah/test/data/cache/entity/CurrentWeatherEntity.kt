package com.syarah.test.data.cache.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class CurrentWeatherEntity(
    @Id
    var id:Long=0,
    val temp: Double,
    val main: String,
    val description: String,
    val icon: String,
    val weatherId: Int,

    )
