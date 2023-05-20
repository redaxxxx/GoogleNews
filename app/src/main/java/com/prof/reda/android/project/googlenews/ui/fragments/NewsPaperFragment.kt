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
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.prof.reda.android.project.googlenews.BuildConfig
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.TAG
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter
import com.prof.reda.android.project.googlenews.adapters.ViewPagerAdapter
import com.prof.reda.android.project.googlenews.application
import com.prof.reda.android.project.googlenews.databinding.FragmentNewsPaperBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels
import java.text.SimpleDateFormat
import java.util.*


class NewsPaperFragment : Fragment() {
    private lateinit var binding: FragmentNewsPaperBinding
    private lateinit var viewModels: NewsViewModels
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_paper, container, false)
        val factory = NewsViewModelFactory(application)
        viewModels = ViewModelProvider(this, factory).get(NewsViewModels::class.java)

        binding.lifecycleOwner = this
        setupViewpager()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val date:Date = calendar.getTime();
        val format = SimpleDateFormat("yyyy MM dd");
        val dateOutput = format.format(date);


        viewModels.getRecentArticles("bitcoin",dateOutput, "publishedAt", BuildConfig.NEWS_API_KEY).observe(viewLifecycleOwner, Observer {articles->
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

    private fun setupViewpager(){
        binding.tabLayout.apply {
            addTab(binding.tabLayout.newTab().setText("Techno"))
            addTab(binding.tabLayout.newTab().setText("Business"))
            addTab(binding.tabLayout.newTab().setText("Tech"))
        }

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = viewPagerAdapter

        binding.tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}