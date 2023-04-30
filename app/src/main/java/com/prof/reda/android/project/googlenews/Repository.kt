package com.prof.reda.android.project.googlenews

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.prof.reda.android.project.googlenews.models.Article
import com.prof.reda.android.project.googlenews.models.Articles
import com.prof.reda.android.project.googlenews.retrofit.ApiServices
import com.prof.reda.android.project.googlenews.retrofit.Http
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class Repository(application: Application){

     private var _recentArticles:MutableLiveData<List<Article>>
     private var _technologyArticles:MutableLiveData<List<Article>>

     init {
         _recentArticles = MutableLiveData<List<Article>>()
         _technologyArticles = MutableLiveData<List<Article>>()
     }
     fun getRecentArticles(apiKey:String): MutableLiveData<List<Article>>{

        val call = Http.getRetrofit().create(ApiServices::class.java).getRecentArticles(apiKey)
        call.enqueue(object : Callback<Articles>{
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                    if(response.body() != null){
//                    val articles: List<Article> = response.body()!!.articles
//                    _recentArticles.value = articles
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

     fun getTechnologyArticles(apiKey: String): MutableLiveData<List<Article>>{
        val call = Http.getRetrofit().create(ApiServices::class.java).getTechnologyArticles(apiKey)
//         val call = Http.retrofit.create(ApiServices::class.java).getTechnologyArticles(apiKey)
        call.enqueue(object : Callback<Articles>{
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                _technologyArticles.value = response.body()!!.articles
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        })

        return _technologyArticles
    }
 }