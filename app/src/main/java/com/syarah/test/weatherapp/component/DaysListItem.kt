package com.syarah.test.weatherapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.weatherapp.convertLongToTime
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun DayListItem(forecast: Forecast) {

    var sdf: SimpleDateFormat = SimpleDateFormat("EEEE")
    var d: Date = convertLongToTime(forecast.date)
    var dayOfTheWeek: String = sdf.format(d)

    Surface(
        modifier = Modifier
            .height(208.dp)
            .width(140.dp)
            .padding(10.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayOfTheWeek,
                style = MaterialTheme.typography.subtitle1,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )
            Text(
                text = forecast.maxTemp.toString(),
                style = MaterialTheme.typography.subtitle1,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .size(34.dp)
                            .padding(bottom = 8.dp),
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription =
                        forecast.minTemp.toString()

                    )
                    Text(
                        text = forecast.minTemp.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.paddingFromBaseline(24.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .size(34.dp)
                            .padding(bottom = 8.dp),
//                        painter = painterResource(id = R.drawable.{ forecast.icon }),
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription =
                        forecast.maxTemp.toString()

                    )
                    Text(
                        text = forecast.maxTemp.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.paddingFromBaseline(24.dp)
                    )
                }
            }
        }
    }
}





