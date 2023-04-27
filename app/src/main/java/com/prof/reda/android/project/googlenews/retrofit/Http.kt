package com.prof.reda.android.project.googlenews.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.prof.reda.android.project.googlenews.APPLICATION_JSON
import com.prof.reda.android.project.googlenews.BASE_URL
import com.prof.reda.android.project.googlenews.MULTIPART
import com.prof.reda.android.project.googlenews.TAG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Http {

    private var retrofit: Retrofit? = null

    private fun initialize() {
        val gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(getHeader())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    fun <T> create(`object`: Class<T>?): T {
        //initialize(App.getConfig());
        if (retrofit == null) {
            initialize()
            Log.d(TAG, "Initialize retrofit")
        }
        Log.d("Google News", "create retrofit")
        return retrofit!!.create(`object`!!)
    }

    private fun getHeader(): OkHttpClient {
        Log.d("Google News", "access method getHeader")
        val loggingInterceptor = HttpLoggingInterceptor { s -> Log.e("Interceptor", s) }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(Interceptor { chain ->
                var request = chain.request()
                val requestBuild: Request.Builder = request.newBuilder()
                requestBuild.addHeader("Content_Type", MULTIPART)
                requestBuild.addHeader("ACCEPT", APPLICATION_JSON)
                request = requestBuild.build()
                chain.proceed(request)
            }).build()
    }
}
