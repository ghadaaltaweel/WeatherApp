package com.syarah.test.weatherapp

import androidx.lifecycle.viewModelScope
import com.syarah.test.core.model.currentWeather.CurrentWeather
import com.syarah.test.core.model.currentWeather.forecastWeather.City
import com.syarah.test.core.model.currentWeather.forecastWeather.Forecast
import com.syarah.test.core.usecase.GetCurrentWeatherUseCase
import com.syarah.test.core.usecase.GetForecastWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastWeatherUseCase: GetForecastWeatherUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        actionTrigger(UIAction.GetCurrentWeather(0.0, 0.0))
    }

    data class UiState(
        val currentWeather: CurrentWeather = CurrentWeather(
            temp = 0.0,
            weatherId = -1,
            main = "",
            description = "",
            icon = ""
        ),
        val forecastWeather: List<Forecast> = emptyList(),
        val forecastCity: City = City(id = -1, name = "", country = ""),
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val errorMessage: String = "",
    )


    sealed class UIAction {
        data class GetCurrentWeather(val lat: Double, val long: Double) : UIAction()
        data class GetForecastWeather(val lat: Double, val long: Double) : UIAction()
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
            }
        }
    }

    private suspend fun getCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            handleResult(getCurrentWeatherUseCase(lat, long),
                { data ->
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

    private suspend fun getForecastWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            handleResult(getForecastWeatherUseCase(lat, long),
                { data ->
                    viewModelScope.launch {
                        _uiState.emit(
                            _uiState.value.copy(
                                forecastWeather = data.forecastList,
                                forecastCity = data.city,
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