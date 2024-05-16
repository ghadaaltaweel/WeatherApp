package com.syarah.test.weatherapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.weatherapp.R
import com.syarah.test.weatherapp.convertLongToTime
import com.syarah.test.weatherapp.getDayNameFromLongDate
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun DayListItem(forecast: Forecast) {


    Surface(
        modifier = Modifier
            .height(208.dp)
            .width(140.dp)
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Column(
            Modifier.background(MaterialTheme.colorScheme.secondary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = forecast.date.getDayNameFromLongDate(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )

            Row {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = forecast.maxTemp.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.paddingFromBaseline(24.dp)
                    )
                    Icon(
                        painter = if (forecast.minTemp < 100) painterResource(id = R.drawable.ic_cloud) else painterResource(
                            id = R.drawable.ic_sun
                        ),
                        contentDescription = null
                    )
                    Text(
                        text = forecast.minTemp.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.paddingFromBaseline(24.dp)
                    )
                }
            }
        }
    }
}






