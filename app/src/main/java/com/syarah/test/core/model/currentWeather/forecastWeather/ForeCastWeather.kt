package com.syarah.test.core.model.currentWeather.forecastWeather

data class ForeCastWeather(

    val message:String,
    val forecastList:List<Forecast>,
    val city:City
)
