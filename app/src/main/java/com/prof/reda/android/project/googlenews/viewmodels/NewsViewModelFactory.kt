package com.prof.reda.android.project.googlenews.viewmodels

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prof.reda.android.project.googlenews.application

class NewsViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModels(application) as (T)

    }
}