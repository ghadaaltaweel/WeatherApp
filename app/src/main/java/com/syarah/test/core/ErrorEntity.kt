package com.syarah.test.core

sealed class ErrorEntity{

    object NoConnection : ErrorEntity()
    object NoAuthoriseSession : ErrorEntity()
    object Unknown : ErrorEntity()

    data class ApiError(val code: Int, val message: List<String>) : ErrorEntity()
    data class InternalError(  val message: String) : ErrorEntity()


    object None : ErrorEntity()
}
