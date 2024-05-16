package com.syarah.test.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syarah.test.weatherapp.MainViewModel
import com.syarah.test.weatherapp.component.CurrentWeatherCard
import com.syarah.test.weatherapp.component.DayListItem
import com.syarah.test.weatherapp.component.SearchComponent


@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    uiState: State<MainViewModel.UiState>,
    onValueChange: (String) -> Unit
) {


    val currentWeather = uiState.value.currentWeather
    val forecast = uiState.value.forecastWeather
    Surface(color = MaterialTheme.colorScheme.surface) {
        Box {
            Scaffold(
                topBar = {
                }
            ) {padding ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchComponent(onValueChange = {
                        onValueChange(it)
                    })
                    Box(
                        Modifier
                            .padding(start = 8.dp)
                    ) {
                        CurrentWeatherCard(currentWeather)
                    }

                    LazyRow {
                        items(forecast.size) { item ->
                            Box(
                                Modifier
                                    .padding(start = 8.dp)
                                    .background(MaterialTheme.colorScheme.secondary)
                            ) {
                                DayListItem(forecast =forecast[item] )
                            }
                        }
                    }
                }
            }
        }
    }
}




