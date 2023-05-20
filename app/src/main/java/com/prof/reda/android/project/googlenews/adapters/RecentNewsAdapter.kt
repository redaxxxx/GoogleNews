package com.prof.reda.android.project.googlenews.adapters

import android.content.Context
import android.text.format.DateFormat.getTimeFormat
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.TAG
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter.NewsViewHolder
import com.prof.reda.android.project.googlenews.databinding.RecentNewsItemsBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.squareup.picasso.Picasso

class RecentNewsAdapter(
    private val mContext: Context,
    private val articleList: List<Article>
) :
    RecyclerView.Adapter<NewsViewHolder>() {
    class NewsViewHolder(val binding: RecentNewsItemsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        Log.d(TAG, "work on adapter")
        return NewsViewHolder(
            RecentNewsItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.binding.shareBtn.tag = position
        holder.binding.bookmarkBtn.tag = position

        if (articleList.size > 0 && articleList != null) {
            val item = articleList[position]

            Log.i(TAG, "author " + item.author + "urlToImage " + item.urlToImage)
            if (item.author == "null") {
                holder.binding.nameTv.text = mContext.resources.getString(R.string.author_not_found)
            } else {
                holder.binding.nameTv.text = item.author
            }

            if (item.urlToImage!= null && item.urlToImage != "null") {
                Picasso.with(mContext)
                    .load(item.urlToImage)
                    .into(holder.binding.imageView)
            }
        }
    }

//    private fun getTimeFormat(time: Long): String {
//        val timeNow = System.currentTimeMillis()
//
//        val SECOND_MILLIS = 1000
//        val MINUTE_MILLIS: Int = 60 * SECOND_MILLIS
//        val HOUR_MILLIS: Int = 60 * MINUTE_MILLIS
//        val DAY_MILLIS: Int = 24 * HOUR_MILLIS
//        val diff = timeNow - time
//
//        return if (diff < DateUtils.SECOND_IN_MILLIS) {
//            "just now"
//        } else if (diff < 2 * DateUtils.MINUTE_IN_MILLIS) {
//            "a minute ago"
//        } else if (diff < 50 * DateUtils.MINUTE_IN_MILLIS) {
//            (diff / MINUTE_MILLIS).toString() + " minutes ago"
//        } else if (diff < 90 * DateUtils.MINUTE_IN_MILLIS) {
//            "an hour ago"
//        } else if (diff < 24 * DateUtils.HOUR_IN_MILLIS) {
//            (diff / HOUR_MILLIS).toString() + " hours ago"
//        } else if (diff < 48 * DateUtils.HOUR_IN_MILLIS) {
//            "yesterday"
//        } else (diff / DateUtils.DAY_IN_MILLIS).toString() + " days ago"
//
//    }
}