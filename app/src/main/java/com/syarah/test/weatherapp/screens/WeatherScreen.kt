package com.syarah.test.weatherapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.syarah.test.weatherapp.MainViewModel
import com.syarah.test.weatherapp.component.CurrentWeatherCard
import com.syarah.test.weatherapp.component.DayListItem


@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    uiState: State<MainViewModel.UiState>,
    onValueChange: (String) -> Unit
) {


    val currentWeather = uiState.value.currentWeather
    val forecast = uiState.value.forecastWeather
    Surface(color = MaterialTheme.colors.background) {
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
                        Log.d("ghada", "WeatherScreen: $it")
                    })
                    Box(
                        Modifier
                            .padding(start = 8.dp)
                            .clickable(
                                onClick = {
                                    /* coroutineScope.launch {
                                        selectedWeather.value = currentWeather
                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                        } else {
                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                        }
                                    }*/
                                }
                            )
                    ) {
                        CurrentWeatherCard(currentWeather)
                    }


                    LazyRow {
                        items(forecast.size) { item ->
                            Box(
                                Modifier
                                    .padding(start = 8.dp)
                                    .clickable(
                                        onClick = {

                                        }
                                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    onValueChange: (String) -> Unit
) {

    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.surface)
            .padding(10.dp)
            .height(45.dp),
        value = text,
        onValueChange = {
            text = it
          //  onValueChange(it.text)
        },
        shape = RoundedCornerShape(8.dp),
        textStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            IconButton(onClick = {
                onValueChange(text.text)
            }
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                    text = TextFieldValue("")
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
            }
        },
        placeholder = {
            androidx.compose.material3.Text(
                text = "Search by Country",
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
            )
        }
    )
}


