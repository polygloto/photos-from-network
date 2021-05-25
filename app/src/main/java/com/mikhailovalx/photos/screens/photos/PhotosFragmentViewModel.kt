package com.mikhailovalx.photos.screens.photos

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovalx.photos.utilits.USERS_INPUT_QUERY
import com.mikhailovalx.photos.data.Photo
import com.mikhailovalx.photos.data.PhotosSearchResponse
import com.mikhailovalx.photos.network.NetworkClient
import com.mikhailovalx.photos.utilits.APP_ACTIVITY
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class PhotosFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mutablePhotosListLiveData = MutableLiveData<List<Photo>>()
    private val photosListLiveData: LiveData<List<Photo>> = mutablePhotosListLiveData
    private val compositeDisposable = CompositeDisposable()

    var photosAdapter = PhotosAdapter()

    fun loadPhotos(findByUserQuery: Boolean = false): LiveData<List<Photo>> {

        val queryFunction =
            if (findByUserQuery) NetworkClient.client.getImages(USERS_INPUT_QUERY) else NetworkClient.client.getRandomImages()

        compositeDisposable.add(queryFunction
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                displayPhotos(it)
            }, {
                Toast.makeText(APP_ACTIVITY, it.message, Toast.LENGTH_LONG).show()
            }))

        return photosListLiveData

    }

    private fun displayPhotos(searchResponse: PhotosSearchResponse){
        val photosList = searchResponse.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }

        mutablePhotosListLiveData.postValue(photosList)
    }

    override fun onCleared() {
        // Destroys requests so that they do not remain in memory.
        compositeDisposable.dispose()
        super.onCleared()
    }
}