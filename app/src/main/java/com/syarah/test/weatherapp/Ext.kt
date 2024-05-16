package com.syarah.test.weatherapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
fun Long.getDayNameFromLongDate(): String {

    val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
    val date = Date(this * 1000)
    return sdf.format(date)
}

fun Long.getTimeNameFromLongDate(): String {

    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(this * 1000)
    return sdf.format(date)
}