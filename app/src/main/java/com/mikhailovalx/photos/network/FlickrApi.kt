package com.mikhailovalx.photos.network

import com.mikhailovalx.photos.FLICKR_API
import com.mikhailovalx.photos.USERS_INPUT_QUERY
import com.mikhailovalx.photos.data.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$FLICKR_API")
    suspend fun getImages(@Query("text") text: String): PhotosSearchResponse

    @GET("?method=flickr.interestingness.getList&format=json&nojsoncallback=1&api_key=$FLICKR_API")
    suspend fun getRandomImages(): PhotosSearchResponse
}