package com.mikhailovalx.photos.screens.photos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovalx.photos.data.Photo
import com.mikhailovalx.photos.network.NetworkClient
import kotlinx.coroutines.launch

class PhotosFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mutablePhotosListLiveData = MutableLiveData<List<Photo>>()
    private val photosListLiveData: LiveData<List<Photo>> = mutablePhotosListLiveData

    var photosAdapter = PhotosAdapter()

    fun loadPhotos(): LiveData<List<Photo>>{
        viewModelScope.launch {
            val searchResponse = NetworkClient.client.getImages()
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }

            mutablePhotosListLiveData.postValue(photosList)

        }

        return  photosListLiveData

    }
}