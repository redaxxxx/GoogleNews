package com.prof.reda.android.project.googlenews.adapters

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.prof.reda.android.project.googlenews.adapters.RecentNewsAdapter.NewsViewHolder
import com.prof.reda.android.project.googlenews.databinding.NewsItemsBinding
import com.prof.reda.android.project.googlenews.models.Article
import com.squareup.picasso.Picasso
import java.sql.Time

class RecentNewsAdapter(private val mContext: Context, private var data: List<Article>) : RecyclerView.Adapter<NewsViewHolder>() {

    fun setArticlesList(articles: List<Article>) {
        this.data = articles
        notifyDataSetChanged()
    }

    class NewsViewHolder(val binding: NewsItemsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        if (data.isNotEmpty()) {
            val item = data[position]

            val firstPart = item.publishedAt.substring(0,11)
            val secondPart = item.publishedAt.substring(11,19)

            val time = Time.valueOf(secondPart)

            holder.binding.dateTv.text = getTimeFormat(time.time)


            Picasso.with(mContext)
                .load(item.urlToImage)
                .into(holder.binding.imageView)

            holder.binding.nameTv.text = item.source.name

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