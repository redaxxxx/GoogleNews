package com.prof.reda.android.project.googlenews.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.MediaColumns.AUTHOR
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prof.reda.android.project.googlenews.*
import com.prof.reda.android.project.googlenews.adapters.NewsAdapter
import com.prof.reda.android.project.googlenews.databinding.FragmentTechBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.prof.reda.android.project.googlenews.ui.activities.NewsDetails
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModelFactory
import com.prof.reda.android.project.googlenews.viewmodels.NewsViewModels

class TechFragment : Fragment(), NewsAdapter.OnClickListenerItem, NewsAdapter.OnShareNewUrl {
    private lateinit var binding: FragmentTechBinding
    private lateinit var viewModel: NewsViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech, container, false)
        val factory = NewsViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(NewsViewModels::class.java)

        binding.lifecycleOwner = this

        viewModel.getTechArticles(BuildConfig.NEWS_API_KEY)
            .observe(viewLifecycleOwner){articles ->
            prepareRecyclerView(articles)
        }

        return binding.root
    }

    private fun prepareRecyclerView(articles: List<Article>){
        val adapter = context?.let { NewsAdapter(it, articles,this,this) }
        binding.techRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.techRv.setHasFixedSize(true)
        binding.techRv.itemAnimator = DefaultItemAnimator()
        binding.techRv.adapter = adapter
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