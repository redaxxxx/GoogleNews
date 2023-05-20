package com.prof.reda.android.project.googlenews.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.prof.reda.android.project.googlenews.data.Repository
import com.prof.reda.android.project.googlenews.data.models.Article
import kotlinx.coroutines.*

class NewsViewModels(application: Application) : ViewModel() {

    private val repository: Repository
    private var recentArticles= MutableLiveData<List<Article>>()
    private var technologyArticles= MutableLiveData<List<Article>>()
    private var businessArticles= MutableLiveData<List<Article>>()
    private var techArticles= MutableLiveData<List<Article>>()

    init {
        repository = Repository(application)
    }
    fun getRecentArticles(query:String, date:String, sortBy:String, apiKey: String): MutableLiveData<List<Article>>{

        viewModelScope.launch {
             recentArticles = repository.getRecentArticles(query, date, sortBy, apiKey)
        }
        return recentArticles
    }

    fun getTechnologyArticles(query: String, dateFrom:String, dateTo:String,
                              sortBy: String, apiKey: String): MutableLiveData<List<Article>>{
        viewModelScope.launch {
            technologyArticles = repository.getTechnologyArticles(query, dateFrom, dateTo, sortBy, apiKey)
        }
        return technologyArticles
    }

    fun getBusinessArticles(apiKey: String): MutableLiveData<List<Article>>{
        viewModelScope.launch {
            businessArticles = repository.getBusinessArticles(apiKey)
        }
        return businessArticles
    }

    fun getTechArticles(apiKey: String): MutableLiveData<List<Article>>{
        viewModelScope.launch {
            techArticles = repository.getTechArticles(apiKey)
        }
        return techArticles
    }
}