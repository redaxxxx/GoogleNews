package com.prof.reda.android.project.googlenews.adapters

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.TAG
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter.NewsViewHolder
import com.prof.reda.android.project.googlenews.databinding.NewsItemsBinding
import com.prof.reda.android.project.googlenews.models.Article
import com.squareup.picasso.Picasso
import java.sql.Time
class RecentNewsAdapter(private val mContext: Context, private val articleList: List<Article> = arrayListOf()) :
    RecyclerView.Adapter<NewsViewHolder>() {
    class NewsViewHolder(val binding: NewsItemsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        Log.d(TAG, "work on adapter")
        return NewsViewHolder(NewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent,
            false))
    }
    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder")
        val item = articleList[position]

        if (item.source.name != null){
            if (item.source.name == "null"){
                mContext.resources.getString(R.string.author_not_found)
            }else{
                holder.binding.nameTv.text = item.source.name
            }
            if (item.publishedAt != null && item.url != null && item.urlToImage != null){
                if (item.publishedAt == "null"){
                    holder.binding.dateTv.text = mContext.resources.getString(R.string.author_not_found)
                }else{
                    val secondPart = item.publishedAt.substring(11,19)

                    val time = Time.valueOf(secondPart)

                    holder.binding.dateTv.text = getTimeFormat(time.time)
                }
                if(item.urlToImage.isNotEmpty() && item.urlToImage != "null"){
                    Picasso.with(mContext)
                        .load(item.urlToImage)
                        .into(holder.binding.imageView)
                }
            }
        }
    }

    private fun getTimeFormat(time:Long):String{
        val AVERAGE_MONTH_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 30
        val timeNow = System.currentTimeMillis()
        val delta = timeNow - time
        val resolution:Long
        if (delta <= DateUtils.MINUTE_IN_MILLIS){
            resolution = DateUtils.SECOND_IN_MILLIS
        }else if (delta <= DateUtils.HOUR_IN_MILLIS){
            resolution = DateUtils.MINUTE_IN_MILLIS
        }else if (delta <= DateUtils.DAY_IN_MILLIS){
            resolution = DateUtils.HOUR_IN_MILLIS
        }else if (delta <= DateUtils.WEEK_IN_MILLIS){
            resolution = DateUtils.DAY_IN_MILLIS
        }else if (delta <= AVERAGE_MONTH_IN_MILLIS){
            return (delta / DateUtils.WEEK_IN_MILLIS).toString() + " weeks(s) ago"
        }else if (delta <= DateUtils.YEAR_IN_MILLIS){
            return (delta / AVERAGE_MONTH_IN_MILLIS).toString() + " month(s) ago"
        }else{
            return (delta / DateUtils.YEAR_IN_MILLIS).toString() + " year(s) ago"
        }

        return DateUtils.getRelativeTimeSpanString(time, timeNow, resolution).toString()
    }
}