package com.example.ass_androidnetworking_kotlin.Data.source.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppFactory {
    companion object{
        val instance: AppService by lazy {
            retrofitBuilder.create(AppService::class.java)
        }
        private  const val TIME_OUT =15000L
        private val client = OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel((HttpLoggingInterceptor.Level.BODY)))
            .build()
    private val retrofitBuilder= Retrofit.Builder()
        .baseUrl("https://www.google.com.vn")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    }

}