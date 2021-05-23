package com.mikhailovalx.photos.network

import com.mikhailovalx.photos.FLICKR_API
import com.mikhailovalx.photos.data.PhotosSearchResponse
import retrofit2.http.GET

interface FlickrApi {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=cars&api_key=$FLICKR_API")
    suspend fun getImages(): PhotosSearchResponse
}