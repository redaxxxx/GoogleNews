package com.prof.reda.android.project.googlenews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prof.reda.android.project.googlenews.BuildConfig
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.TAG
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter
import com.prof.reda.android.project.googlenews.application
import com.prof.reda.android.project.googlenews.databinding.FragmentNewsPaperBinding
import com.prof.reda.android.project.googlenews.models.Article
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels


class NewsPaperFragment : Fragment() {
    private lateinit var binding: FragmentNewsPaperBinding
    private lateinit var viewModels: NewsViewModels
    private var articles:List<Article> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_paper, container, false)
        val factory = NewsViewModelFactory(application)
        viewModels = ViewModelProvider(this, factory).get(NewsViewModels::class.java)

        binding.lifecycleOwner = this
//        viewModels.getRecentArticles(BuildConfig.NEWS_API_KEY).observe(viewLifecycleOwner){
//            Log.d(TAG, "observe change in data")
//            prepareRecyclerView(it)
//        }
        viewModels.getRecentArticles(BuildConfig.NEWS_API_KEY).observe(viewLifecycleOwner, Observer {articles->
            prepareRecentRecyclerView(articles)
        })

        return binding.root
    }

    private fun prepareRecentRecyclerView(articleList: List<Article>) {
        Log.d(TAG, "work on RecyclerView")
        binding.morningNewsRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,
            false)
        binding.morningNewsRv.setHasFixedSize(true)
        binding.morningNewsRv.itemAnimator = DefaultItemAnimator()

        val recentNewsAdapter = context?.let { RecentNewsAdapter(it, articleList) }
        binding.morningNewsRv.adapter = recentNewsAdapter

    }
}