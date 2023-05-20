package com.prof.reda.android.project.googlenews

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.prof.reda.android.project.googlenews.databinding.NewsItemsBinding
import java.text.SimpleDateFormat
import java.util.*

const val BASE_URL: String = "https://newsapi.org/"
const val MULTIPART: String = "multipart/form-data"
const val APPLICATION_JSON: String = "application/json"
const val TAG: String = "GoogleNews"
val application = Application()
const val URL_IMAGE: String = "com.prof.reda.android.project.googlenews.urltoimage"
const val PUBLISHED_AT: String = "com.prof.reda.android.project.googlenews.publishedAt"
const val TITLE: String = "com.prof.reda.android.project.googlenews.title"
const val NEWS_NAME: String = "com.prof.reda.android.project.googlenews.name"
const val DESCRIPTION: String = "com.prof.reda.android.project.googlenews.description"
class NewsViewHolder(val binding: NewsItemsBinding) : ViewHolder(binding.root)
