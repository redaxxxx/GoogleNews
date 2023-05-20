package com.prof.reda.android.project.googlenews.data.retrofit

import com.prof.reda.android.project.googlenews.data.models.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("v2/everything")
    fun getRecentArticles(@Query("q") query:String, @Query("from") date:String, @Query("sortBy") sortBy:String
                          ,@Query("apiKey")  apiKey: String): Call<Articles>

    @GET("v2/everything")
    fun getTechnologyArticles(@Query("q") query: String, @Query("from") dateFrom:String,
                              @Query("to") dateTo:String, @Query("sortBy") sortBy: String, @Query("apiKey")  apiKey: String): Call<Articles>

    @GET("v2/top-headlines?country=us&category=business")
    fun getBusinessArticles(@Query("apiKey") apiKey: String): Call<Articles>

    @GET("v2/top-headlines?sources=techcrunch")
    fun getTechArticles(@Query("apiKey") apiKey: String): Call<Articles>
}