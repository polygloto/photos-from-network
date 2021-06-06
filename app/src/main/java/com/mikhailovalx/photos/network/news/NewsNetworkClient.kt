package com.mikhailovalx.photos.network.news

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.mikhailovalx.photos.utilits.CONNECTION_TIMEOUT_MS
import com.mikhailovalx.photos.utilits.NEWS_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NewsNetworkClient {
    //Used a delegate to initialize the variable once.
    //The next call will return the value without reinitialization.
    val client: NewsApi by lazy {
        Retrofit.Builder().baseUrl(NEWS_BASE_URL)
            .addConverterFactory( // Conversion support. To turn json into an object.
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .setLenient()
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY // Level.BODY - to get full-scale logs
                }).build()
            )
            .build()
            .create(NewsApi::class.java)
    }
}