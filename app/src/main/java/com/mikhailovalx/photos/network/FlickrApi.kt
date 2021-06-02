package com.mikhailovalx.photos.network

import com.mikhailovalx.photos.utilits.FLICKR_API
import com.mikhailovalx.photos.data.PhotosSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    // From Flickr, json is returned inside jsonFlickrApi()
    // To avoid this problem, the nojsoncallback parameter is used.

    @GET("?method=flickr.photos.search")
    fun getImages(
        @Query("text") text: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("api_key") api_key: String = FLICKR_API,
    ): Single<PhotosSearchResponse>

    @GET("?method=flickr.interestingness.getList")
    fun getRandomImages(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("api_key") api_key: String = FLICKR_API
    ): Single<PhotosSearchResponse>
}