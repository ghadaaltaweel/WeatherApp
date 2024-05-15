package com.syarah.test.core

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity

}