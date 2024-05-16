package com.syarah.test.weatherapp

import java.text.SimpleDateFormat
import java.util.Date


fun celsiusToFahrenheit(celsius: Double): Double {
    return (celsius * 9 / 5) + 32
}

fun fahrenheitToCelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32) * 5 / 9
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
fun convertLongToTime(time: Long): Date {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd")
    return date
}