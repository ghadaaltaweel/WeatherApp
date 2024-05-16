package com.syarah.test.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.syarah.test.weatherapp.screens.WeatherScreen
import com.syarah.test.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContainer(mainViewModel, onValueChange = {
                        val pairOfLocation = getLocationFromAddress(this, it)
                        mainViewModel.actionTrigger(
                            MainViewModel.UIAction.GetCurrentWeather(
                                pairOfLocation.first,
                                pairOfLocation.second
                            )
                        )

                    })
                }
            }

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            obtieneLocalizacion()
        }
    }

    @SuppressLint("MissingPermission")
    private fun obtieneLocalizacion() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        mainViewModel.actionTrigger(
                            MainViewModel.UIAction.GetCurrentWeather(
                                latitude,
                                longitude
                            )
                        )
                        Log.d(
                            "Location",
                            "Location for current location => Latitude: $latitude, Longitude: $longitude"
                        )
                    } else {
                        // Location not found, handle the case
                        Log.d("Location", "Location not found")
                    }
                }
        } else {
            // Request location permissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    private fun getLocationFromAddress(context: Context, city: String): Pair<Double, Double> {
        val geocoder = Geocoder(context)
        var latitude: Double = 0.0
        var longitude: Double = 0.0
        try {
            val addressList = geocoder.getFromLocationName(city, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                latitude = addressList[0].latitude
                longitude = addressList[0].longitude
                // Now you have latitude and longitude
                // Do something with them
                Toast.makeText(
                    context,
                    "Latitude: $latitude, Longitude: $longitude",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "Location",
                    "Location for $city => Latitude: $latitude, Longitude: $longitude"
                )
            } else {
                // Handle no results found
                Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Pair(latitude, longitude)

    }
}

@Composable
fun MainContainer(
    mainViewModel: MainViewModel,
    onValueChange: (String) -> Unit
) {
    val uiState = mainViewModel.uiState.collectAsState()
    WeatherScreen(uiState, onValueChange = {
        onValueChange(it)

    })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {

    }
}


