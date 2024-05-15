package com.syarah.test.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.syarah.test.data.api.ApiClient
import com.syarah.test.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    @ApiURL
    fun provideApiUrl() = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @ApiURL apiUrl: String,
        gson: Gson
    ): Retrofit {
        dummyRequest(okHttpClient)

        return with(Retrofit.Builder()) {
            addConverterFactory(GsonConverterFactory.create(gson))
            baseUrl(apiUrl)
            client(okHttpClient)
            build()
        }
    }


    @Singleton
    @Provides
    @AuthToken
    fun provideAuthInterceptor(
    ): Interceptor = AuthInterceptor()
    @Provides
    @Singleton
    fun provideHttpLogInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun dummyRequest(okHttpClient: OkHttpClient) {
        val dummyRequest: Request = Request.Builder().url("http://127.0.0.1").build()
        okHttpClient.newCall(dummyRequest)
    }
    @Provides
    @Singleton
    fun provideOkHttp(
//        @AuthToken authTokenInterceptor: Interceptor,
//        @ProvideAuthToken authToken: Authenticator,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = with(OkHttpClient.Builder()) {
//        addInterceptor(authTokenInterceptor)
//        authenticator(authToken)
        connectTimeout(2, TimeUnit.MINUTES)
        readTimeout(2, TimeUnit.MINUTES)
        writeTimeout(2, TimeUnit.SECONDS)
        addInterceptor(httpLoggingInterceptor)
        build()
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiURL

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
private annotation class AuthToken