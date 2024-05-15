package com.syarah.test.data.api

import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ResultData
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

    fun <T> Response<T>.validate(
    ): ResultData<T> {

        return try {

            return if (isSuccessful) {
                ResultData.Success(body() as T)
            } else {
                ResultData.Error(
                    when (code()) {
                        401 ->
                            ErrorEntity.NoAuthoriseSession

                        400 -> {
                            val errorMessage = try {
                                errorBody()?.string() ?: "Something went wrong"
                            } catch (e: Exception) {
                                "Something went wrong"
                            }
                            ErrorEntity.ApiError(
                                code(),
                                listOf(errorMessage)
                            )
                        }
                        403 ->{
                            val errorMessage = try {
                                errorBody()?.string() ?: "Something went wrong"
                            } catch (e: Exception) {
                                "Something went wrong"
                            }
                            ErrorEntity.ApiError(
                                code(),
                                listOf(errorMessage)
                            )
                        }

                        else ->
                            ErrorEntity.ApiError(
                                code(),
                                listOf("")
                            )

                    }
                )
            }
        } catch (e: Throwable) {
            getError(e).let {
                ResultData.Error(it)
            }
        }

    }


fun getError(throwable: Throwable): ErrorEntity {
    return when (throwable) {
        is IOException -> ErrorEntity.NoConnection
        is HttpException -> {
            when (throwable.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.NoAuthoriseSession
                else -> ErrorEntity.Unknown
            }
        }

        else -> ErrorEntity.Unknown
    }
}