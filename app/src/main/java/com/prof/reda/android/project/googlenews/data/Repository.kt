package com.prof.reda.android.project.googlenews.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.prof.reda.android.project.googlenews.TAG
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.data.models.Articles
import com.prof.reda.android.project.googlenews.data.retrofit.ApiServices
import com.prof.reda.android.project.googlenews.data.retrofit.Http
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class Repository(application: Application){

     private var _recentArticles:MutableLiveData<List<Article>>
     private var _technologyArticles:MutableLiveData<List<Article>>
     private var _businessArticles:MutableLiveData<List<Article>>
     private var _techArticles:MutableLiveData<List<Article>>

     init {
         _recentArticles = MutableLiveData<List<Article>>()
         _technologyArticles = MutableLiveData<List<Article>>()
         _businessArticles = MutableLiveData<List<Article>>()
         _techArticles = MutableLiveData<List<Article>>()
     }
     fun getRecentArticles(query:String, date:String, sortBy:String, apiKey:String): MutableLiveData<List<Article>>{

        val call = Http.getRetrofit().create(ApiServices::class.java)
            .getRecentArticles(query,date, sortBy, apiKey)

        call.enqueue(object : Callback<Articles>{
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                    if(response.body() != null){
                        _recentArticles.value = response.body()!!.articles

                        Log.d("Google News", "response body "+response.body().toString())
                    }

                    else Log.d("Google News", "can't fetch any data")
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Log.d("Google News", "fail call "+t.message.toString())
            }

        })
        return _recentArticles
    }

     fun getTechnologyArticles(query: String, dateFrom:String, dateTo:String,
                               sortBy: String, apiKey: String): MutableLiveData<List<Article>>{
        val call = Http.getRetrofit().create(ApiServices::class.java)
            .getTechnologyArticles(query, dateFrom, dateTo, sortBy, apiKey)
        call.enqueue(object : Callback<Articles>{
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                if (response.body() != null){
                    _technologyArticles.value = response.body()!!.articles
                    Log.d("Google News", "response body "+response.body().toString())
                }else Log.d("Google News", "can't fetch any data")
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        })

        return _technologyArticles
    }

     fun getBusinessArticles(apiKey:String): MutableLiveData<List<Article>>{
         val call = Http.getRetrofit().create(ApiServices::class.java)
             .getBusinessArticles(apiKey)
         call.enqueue(object : Callback<Articles>{
             override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                 if (response.body() != null){
                     _businessArticles.value = response.body()!!.articles
                     Log.d(TAG, "response of business articles"+response.body().toString())
                 }
             }

             override fun onFailure(call: Call<Articles>, t: Throwable) {
                 Log.d(TAG, "Error is "+t.message.toString())
             }
         })
         return _businessArticles
     }

     fun getTechArticles(apiKey: String): MutableLiveData<List<Article>>{
         val call = Http.getRetrofit().create(ApiServices::class.java)
             .getTechArticles(apiKey)
         call.enqueue(object: Callback<Articles>{
             override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                 if (response.body() != null){
                     _techArticles.value = response.body()!!.articles
                 }
             }

             override fun onFailure(call: Call<Articles>, t: Throwable) {
                 Log.d(TAG, t.message.toString())
             }
         })
         return _techArticles
     }
 }