package com.prof.reda.android.project.googlenews.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.prof.reda.android.project.googlenews.*
import com.prof.reda.android.project.googlenews.adapters.NewsAdapter
import com.prof.reda.android.project.googlenews.databinding.FragmentBusinessBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.ui.activities.NewsDetails
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels

class BusinessFragment : Fragment(), NewsAdapter.OnClickListenerItem, NewsAdapter.OnShareNewUrl{
    private lateinit var binding: FragmentBusinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false)
        val factory = NewsViewModelFactory(application)
        val viewModel = ViewModelProvider(this, factory).get(NewsViewModels::class.java)
        binding.lifecycleOwner = this

        viewModel.getBusinessArticles(BuildConfig.NEWS_API_KEY)
            .observe(viewLifecycleOwner) { articles ->
                prepareRecyclerView(articles)
            }

        return binding.root
    }
    private fun prepareRecyclerView(articleList: List<Article>){
        val adapter = context?.let { NewsAdapter(it, articleList,this,this) }
        binding.businessRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.businessRv.setHasFixedSize(true)
        binding.businessRv.itemAnimator = DefaultItemAnimator()
        binding.businessRv.adapter = adapter
    }

    override fun onClick(article: Article) {
        val intent = Intent(activity, NewsDetails::class.java)

        intent.putExtra(URL_IMAGE, article.urlToImage)
        intent.putExtra(PUBLISHED_AT, article.publishedAt)
        intent.putExtra(TITLE, article.title)
        intent.putExtra(NEWS_NAME, article.source.name)
        intent.putExtra(DESCRIPTION, article.description)
        Log.d(TAG, "url to image is ${article.urlToImage}")
        startActivity(intent)
    }
    override fun onShare(article: Article) {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.setType("text/plain")
        sendIntent.putExtra(Intent.EXTRA_TEXT,article.url)
        startActivity(Intent.createChooser(sendIntent, "Share with"))
    }
}