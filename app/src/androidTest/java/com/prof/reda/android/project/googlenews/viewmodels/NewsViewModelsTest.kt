package com.prof.reda.android.project.googlenews.viewmodels

import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prof.reda.android.project.googlenews.BuildConfig
import com.prof.reda.android.project.googlenews.data.models.Article
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsViewModelsTest{

    @Test
    fun getRecentArticles_setNewArticles(){
        val newsViewModels = NewsViewModels(ApplicationProvider.getApplicationContext())
        newsViewModels.getRecentArticles(BuildConfig.NEWS_API_KEY)
    }
}