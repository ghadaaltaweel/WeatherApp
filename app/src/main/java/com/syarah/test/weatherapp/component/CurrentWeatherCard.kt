package com.syarah.test.weatherapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.weatherapp.celsiusToFahrenheit
import com.syarah.test.weatherapp.fahrenheitToCelsius
import com.syarah.test.weatherapp.round
import kotlin.math.round


@Composable
fun CurrentWeatherCard(currentWeather: CurrentWeather) {

    var tempVal by remember {
        mutableStateOf(round(currentWeather.temp) )
    }
    var isfehrenhite by remember {
        mutableStateOf(true)
    }
    tempVal = currentWeather.temp
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = shapes.medium,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = currentWeather.countryName,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = if (isfehrenhite) "${round(tempVal) }f" else "${round(tempVal) }c",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                SwitchComponent(
                    onChecked = {
                        tempVal = if (it)
                        {
                            isfehrenhite=false
                            tempVal =  fahrenheitToCelsius(tempVal).round(2)
                           fahrenheitToCelsius(tempVal).round(2)
                        }
                        else
                        {
                            isfehrenhite=true
                            tempVal =   celsiusToFahrenheit(tempVal).round(2)
                            celsiusToFahrenheit(tempVal).round(2)

                        }

                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentWeather.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

@Composable
fun SwitchComponent(
    onChecked: (Boolean) -> Unit

) {
    var checked by remember { mutableStateOf((true)) }

    Switch(
        modifier = Modifier,
        checked = checked,
        onCheckedChange = {
            onChecked(it)
            checked = it
        })

}

