package com.prof.reda.android.project.googlenews.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prof.reda.android.project.googlenews.BuildConfig
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter
import com.prof.reda.android.project.googlenews.databinding.FragmentNewsPaperBinding
import com.prof.reda.android.project.googlenews.models.Article
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels

class NewsPaperFragment : Fragment(){

    private lateinit var binding: FragmentNewsPaperBinding
    private val factory = NewsViewModelFactory(requireContext().applicationContext as Application)
    private val viewModels:NewsViewModels by lazy {
        ViewModelProvider(this, factory)[NewsViewModels::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_news_paper, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModels.getRecentArticles(BuildConfig.NEWS_API_KEY).observe(viewLifecycleOwner){
            prepareRecyclerView(it)
        }
    }

    private fun prepareRecyclerView(articleList:List<Article>){
        val recentNewsAdapter = RecentNewsAdapter(requireContext(), articleList)
        binding!!.morningNewsRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,
            false)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()

            adapter = recentNewsAdapter
        }
    }
}