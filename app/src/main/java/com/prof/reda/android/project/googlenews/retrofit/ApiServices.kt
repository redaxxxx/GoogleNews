package com.prof.reda.android.project.googlenews.retrofit

import com.prof.reda.android.project.googlenews.models.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("v2/everything?q=bitcoin&from=2023-04-01&sortBy=publishedAt")
    fun getRecentArticles(@Query("apiKey")  apiKey: String): Call<Articles>

    @GET("v2/everything?q=apple&from=2023-04-28&to=2023-04-28&sortBy=popularity")
    fun getTechnologyArticles(@Query("apiKey") apiKey: String): Call<Articles>
}