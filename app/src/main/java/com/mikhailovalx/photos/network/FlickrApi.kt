package com.mikhailovalx.photos.network

import com.mikhailovalx.photos.utilits.FLICKR_API
import com.mikhailovalx.photos.utilits.USERS_INPUT_QUERY
import com.mikhailovalx.photos.data.PhotosSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$FLICKR_API")
    fun getImages(@Query("text") text: String): Single<PhotosSearchResponse>

    @GET("?method=flickr.interestingness.getList&format=json&nojsoncallback=1&api_key=$FLICKR_API")
    fun getRandomImages(): Single<PhotosSearchResponse>

}