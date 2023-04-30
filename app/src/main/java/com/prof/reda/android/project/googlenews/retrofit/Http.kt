package com.prof.reda.android.project.googlenews.retrofit

import android.util.Log
import com.prof.reda.android.project.googlenews.APPLICATION_JSON
import com.prof.reda.android.project.googlenews.BASE_URL
import com.prof.reda.android.project.googlenews.MULTIPART
import com.prof.reda.android.project.googlenews.TAG
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Http {

    fun getRetrofit(): Retrofit {
//        val gson = GsonBuilder().setLenient().create()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Log.d(TAG, "create retrofit")
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(getHeader())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
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
            })
            .build()
    }
}
