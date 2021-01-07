package com.wsmodule

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null
    private var baseUrl: String? = null

    fun client(str: String): Retrofit {
        val httpClient = if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .writeTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .connectTimeout(240, TimeUnit.SECONDS)
                .callTimeout(240, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }

        retrofit = Retrofit.Builder()
            .baseUrl(str)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        return this.retrofit!!
    }
}