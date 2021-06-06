package com.mikhailovalx.photos.screens.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovalx.photos.data.news.News
import com.mikhailovalx.photos.data.news.NewsSearchResponse
import com.mikhailovalx.photos.network.news.NewsNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableNewsListLiveData = MutableLiveData<List<News>>()
    val newsListLiveData: LiveData<List<News>> = mutableNewsListLiveData
    var newsAdapter = NewsAdapter()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val newsList = NewsNetworkClient.client.getActualNews().articles
            mutableNewsListLiveData.postValue(newsList)
        }
    }

}