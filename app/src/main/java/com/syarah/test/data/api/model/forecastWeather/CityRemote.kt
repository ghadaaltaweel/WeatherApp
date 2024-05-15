package com.syarah.test.data.api.model.forecastWeather

import com.google.gson.annotations.SerializedName

data class CityRemote(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("country")
    val country: String?

)
