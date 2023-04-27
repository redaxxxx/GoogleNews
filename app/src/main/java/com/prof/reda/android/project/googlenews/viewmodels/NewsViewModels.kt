package com.prof.reda.android.project.googlenews.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.prof.reda.android.project.googlenews.Repository
import com.prof.reda.android.project.googlenews.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModels(application: Application): AndroidViewModel(application) {

    private var repository= Repository(application)
    private var viewModelJob = Job()
    private val coroutineScop = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var recentArticles= MutableLiveData<List<Article>>()
    private var technologyArticles= MutableLiveData<List<Article>>()

    fun getRecentArticles(apiKey: String): MutableLiveData<List<Article>>{

        viewModelScope.launch {
             recentArticles = repository.getRecentArticles(apiKey)
        }

        Log.d("Google News", "get recent articles")
        return recentArticles
    }

    fun getTechnologyArticles(apiKey: String): MutableLiveData<List<Article>>{
        viewModelScope.launch {
            technologyArticles = repository.getTechnologyArticles(apiKey)
        }
        return technologyArticles
    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

}