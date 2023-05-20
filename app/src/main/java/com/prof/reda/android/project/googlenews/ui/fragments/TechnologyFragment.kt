package com.prof.reda.android.project.googlenews.ui.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.MediaColumns.AUTHOR
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prof.reda.android.project.googlenews.*
import com.prof.reda.android.project.googlenews.adapters.NewsAdapter
import com.prof.reda.android.project.googlenews.databinding.FragmentTechnologyBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.ui.activities.NewsDetails
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*


class TechnologyFragment : Fragment(), NewsAdapter.OnClickListenerItem, NewsAdapter.OnShareNewUrl {

    private lateinit var binding: FragmentTechnologyBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_technology, container, false)
        val factory = NewsViewModelFactory(application)
        val viewModel = ViewModelProvider(this, factory).get(NewsViewModels::class.java)
        binding.lifecycleOwner = this

        val timeNow = System.currentTimeMillis()
        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS: Int = 60 * SECOND_MILLIS
        val HOUR_MILLIS: Int = 60 * MINUTE_MILLIS
        val DAY_MILLIS: Int = 24 * HOUR_MILLIS
        val yesterday = timeNow - DAY_MILLIS

        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val yesterdayDate = df.format(yesterday)

        viewModel.getTechnologyArticles("apple", yesterdayDate, yesterdayDate, "popularity",
        BuildConfig.NEWS_API_KEY).observe(viewLifecycleOwner, androidx.lifecycle.Observer{ articles->
            prepareRecyclerView(articles)
        })

        return binding.root
    }
    private fun prepareRecyclerView(articles: List<Article>){
        val newsAdapter = context?.let { NewsAdapter(it, articles,this,this) }
        binding.technologyRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.technologyRV.setHasFixedSize(true)
        binding.technologyRV.itemAnimator = DefaultItemAnimator()
        binding.technologyRV.adapter = newsAdapter
    }

    override fun onClick(article: Article) {
        val intent = Intent(activity, NewsDetails::class.java)

        intent.putExtra(URL_IMAGE, article.urlToImage)
        intent.putExtra(PUBLISHED_AT, article.publishedAt)
        intent.putExtra(TITLE, article.title)
        intent.putExtra(NEWS_NAME, article.source.name)
        intent.putExtra(DESCRIPTION, article.description)
        startActivity(intent)
    }

    override fun onShare(article: Article) {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.setType("text/plain")
        sendIntent.putExtra(Intent.EXTRA_TEXT,article.url)
        startActivity(Intent.createChooser(sendIntent, "Share with"))
    }
}