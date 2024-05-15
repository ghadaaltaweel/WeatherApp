package com.syarah.test.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()


        val requestBuilder = originalRequest.newBuilder()


        val request = requestBuilder.build()
        return chain.proceed(request)


    }
}