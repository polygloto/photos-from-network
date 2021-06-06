package com.mikhailovalx.photos.network.news

import com.mikhailovalx.photos.data.news.NewsSearchResponse
import com.mikhailovalx.photos.utilits.NEWS_API
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getActualNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") apiKey: String = NEWS_API
    ): NewsSearchResponse

}