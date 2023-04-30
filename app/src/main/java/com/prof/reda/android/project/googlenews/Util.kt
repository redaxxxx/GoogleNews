package com.prof.reda.android.project.googlenews

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import com.prof.reda.android.project.googlenews.databinding.NewsItemsBinding

const val BASE_URL: String = "https://newsapi.org/"
const val MULTIPART: String = "multipart/form-data"
const val APPLICATION_JSON: String = "application/json"
const val TAG: String = "GoogleNews"
val application = Application()