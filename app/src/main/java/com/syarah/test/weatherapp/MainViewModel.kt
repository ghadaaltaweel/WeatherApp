package com.syarah.test.weatherapp

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.ForecastCity
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.usecase.GetCurrentWeatherUseCase
import com.syarah.test.core.usecase.GetForecastCityUseCase
import com.syarah.test.core.usecase.GetForecastWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastWeatherUseCase: GetForecastWeatherUseCase,
    private val getForecastCityUseCase: GetForecastCityUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState


    init {
        actionTrigger(UIAction.GetForecastWeather(30.585163,36.238415))

    }

    data class UiState(
        val currentWeather: CurrentWeather = CurrentWeather(
            temp = 0.0,
            weatherId = -1,
            main = "",
            description = "",
            icon = "",
            countryName = "N/A"
        ),
        val forecastWeather: List<Forecast> = emptyList(),
        val forecastCity: ForecastCity = ForecastCity(cityId = -1, cityName = "", cityCountry = ""),
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val errorMessage: String = "",
    )


    sealed class UIAction {
        data class GetCurrentWeather(val lat: Double, val long: Double) : UIAction()
        data class GetForecastWeather(val lat: Double, val long: Double) : UIAction()
        object GetForecastCity :UIAction()
    }

    fun actionTrigger(action: UIAction) {
        viewModelScope.launch {
            when (action) {
                is UIAction.GetCurrentWeather -> {
                    getCurrentWeather(action.lat, action.long)

                }

                is UIAction.GetForecastWeather -> {
                    getForecastWeather(action.lat, action.long)

                }
                is UIAction.GetForecastCity -> {
                    getForecastCity()

                }
            }
        }
    }
    private suspend fun getCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            handleResult(getCurrentWeatherUseCase(lat, long),
                { data ->
                    Log.d("ghada", "getCurrentWeather: $data")

                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                currentWeather = data,
                                isError = false,
                                isLoading = false
                            )
                        )
                    }
                },
                {error ->
                    viewModelScope.launch {
                        Log.d("ghada", "getCurrentWeather: $error")
                        _uiState.emit(
                            _uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = "error is $error"
                            )
                        )
                    }
                }

            )
        }
    }

    private suspend fun getForecastWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            handleResult(getForecastWeatherUseCase(lat, long),
                { data ->
                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                forecastWeather = data,
                                isError = false,
                                isLoading = false
                            )
                        )
                        actionTrigger(UIAction.GetForecastCity)
                    }
                },
                {
                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = "error"
                            )
                        )
                    }
                }

            )
        }
    }

    private suspend fun getForecastCity() {
        viewModelScope.launch {
            handleResult(getForecastCityUseCase(),
                { data ->
                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                forecastCity = data,
                                isError = false,
                                isLoading = false
                            )
                        )
                    }
                },
                {
                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = "error"
                            )
                        )
                    }
                }

            )
        }
    }

}