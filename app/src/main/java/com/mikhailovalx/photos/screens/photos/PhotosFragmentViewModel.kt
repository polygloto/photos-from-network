package com.mikhailovalx.photos.screens.photos

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikhailovalx.photos.data.Photo
import com.mikhailovalx.photos.data.PhotosSearchResponse
import com.mikhailovalx.photos.network.NetworkClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PhotosFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mutablePhotosListLiveData = MutableLiveData<List<Photo>>()
    private val photosListLiveData: LiveData<List<Photo>> = mutablePhotosListLiveData
    private val compositeDisposable = CompositeDisposable()
    private val logTag: String = "Photos ViewModel log"

    var photosAdapter = PhotosAdapter()

    fun loadPhotos(usersQuery: String): LiveData<List<Photo>> {

        val queryFunction =
            if (usersQuery.isEmpty()) NetworkClient.client.getRandomImages()
            else NetworkClient.client.getImages(usersQuery)

        compositeDisposable.add(
            queryFunction
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    displayPhotos(it)
                }, {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()

                    Log.e(logTag, it.message.toString())
                })
        )

        return photosListLiveData

    }

    private fun displayPhotos(searchResponse: PhotosSearchResponse) {
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