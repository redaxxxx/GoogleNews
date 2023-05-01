package com.prof.reda.android.project.googlenews.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.prof.reda.android.project.googlenews.Repository
import com.prof.reda.android.project.googlenews.models.Article
import kotlinx.coroutines.*

class NewsViewModels(application: Application) : ViewModel() {

    private val repository:Repository
    private var recentArticles= MutableLiveData<List<Article>>()
    private var technologyArticles= MutableLiveData<List<Article>>()

    init {
        repository = Repository(application)
    }
    fun getRecentArticles(apiKey: String): MutableLiveData<List<Article>>{

        viewModelScope.launch {
             recentArticles = repository.getRecentArticles(apiKey)
        }
        return recentArticles
    }

    fun getTechnologyArticles(apiKey: String): MutableLiveData<List<Article>>{
        viewModelScope.launch {
            technologyArticles = repository.getTechnologyArticles(apiKey)
        }
        return recentArticles
    }
}