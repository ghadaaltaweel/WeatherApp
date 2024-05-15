package com.syarah.test.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ResultData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    val generalUiState: MutableSharedFlow<ErrorEntity> = MutableSharedFlow()

    fun <T> handleResult(
        result: ResultData<T>,
        onSuccess: (T) -> Unit,
        onApiError: (ErrorEntity) -> Unit
    ) {

        when (result) {
            is ResultData.Error -> {
                when (result.data) {
                    is ErrorEntity.ApiError -> {
                        onApiError(result.data as ErrorEntity.ApiError)
                    }
                    is ErrorEntity.InternalError -> {
                        onApiError(result.data as ErrorEntity.InternalError)
                    }
                    else -> {
                        viewModelScope.launch {
                            generalUiState.emit(result.data)
                        }
                    }
                }
            }
            is ResultData.Success -> onSuccess(result.data)
        }

    }
}