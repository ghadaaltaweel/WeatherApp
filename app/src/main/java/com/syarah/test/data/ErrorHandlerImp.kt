package com.syarah.test.data

import com.syarah.test.core.ErrorEntity
import com.syarah.test.core.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImp: ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.NoConnection
            is HttpException -> {
                when(throwable.code()) {

                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.NoAuthoriseSession

                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}