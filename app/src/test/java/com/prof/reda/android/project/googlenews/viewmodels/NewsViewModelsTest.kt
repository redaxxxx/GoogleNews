package com.prof.reda.android.project.googlenews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prof.reda.android.project.googlenews.BuildConfig
import com.prof.reda.android.project.googlenews.getOrAwaitValue
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.data.models.Articles
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewsViewModelsTest{


    //code for writing multiple viewModel test

    private lateinit var newsViewModels: NewsViewModels

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel(){
        newsViewModels = NewsViewModels(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getRecentArticles_setNewArticles(){
            newsViewModels.getRecentArticles(BuildConfig.NEWS_API_KEY)
            val value = newsViewModels.recentArticles.getOrAwaitValue().isNotEmpty()
            assertThat(value, `is`(true))
    }

    @Test
    fun getTechnologyArticles(){
        newsViewModels.getTechnologyArticles(BuildConfig.NEWS_API_KEY)

        assertThat(newsViewModels.getTechnologyArticles(BuildConfig.NEWS_API_KEY).getOrAwaitValue().isNotEmpty(),
            `is`(true))
    }
}