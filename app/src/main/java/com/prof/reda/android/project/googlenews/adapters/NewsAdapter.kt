package com.prof.reda.android.project.googlenews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prof.reda.android.project.googlenews.NewsViewHolder
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.NewsItemsBinding
import com.prof.reda.android.project.googlenews.data.models.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private val mContext: Context,
    private val mArticles: List<Article>,
    private val onClickListenerItem: OnClickListenerItem,
    private val onshareNewsUrl: OnShareNewUrl
    )
    : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemsBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent
            ,false))
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (mArticles.size > 0 && mArticles != null){
            val item = mArticles[position]

            if (item.title != null){
                if (item.title != "null"){
                    holder.binding.titleTextView.text = item.title
                }else {
                    mContext.resources.getString(R.string.author_not_found)
                }

                if (item.source.name != null && item.source.name != "null"){
                    holder.binding.nameTextView.text = item.source.name
                }else {
                    mContext.resources.getString(R.string.author_not_found)
                }

                if (item.publishedAt != null && item.publishedAt != "null"){

                    holder.binding.publishedAtTv.text = formatDateAsAgo(item.publishedAt)
                }else{
                    mContext.resources.getString(R.string.author_not_found)
                }
                if (item.urlToImage != null && item.urlToImage != "null"){
                    Picasso.with(mContext)
                        .load(item.urlToImage)
                        .into(holder.binding.newsImageView)
                }

                holder.itemView.setOnClickListener {
                    onClickListenerItem.onClick(item)
                }

                holder.binding.shareBtn.setOnClickListener {
                    onshareNewsUrl.onShare(item)
                }
            }


        }
    }

    private fun formatDateAsAgo(format: String): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var result = simpleDateFormat.parse(format)

        val diff = Calendar.getInstance().time.time - result.time

        val oneSec = 1000L
        val oneMin: Long = 60 * oneSec
        val oneHour: Long = 60 * oneMin
        val oneDay: Long = 24 * oneHour
        val oneMonth: Long = 30 * oneDay
        val oneYear: Long = 365 * oneDay

        val diffMin: Long = diff / oneMin
        val diffHours: Long = diff / oneHour
        val diffDays: Long = diff / oneDay
        val diffMonths: Long = diff / oneMonth
        val diffYears: Long = diff / oneYear

        when {
            diffYears > 0 -> {
                return " $diffYears years ago"
            }
            diffMonths > 0 && diffYears < 1 -> {
                return " ${(diffMonths - diffYears / 12)} months ago "
            }
            diffDays > 0 && diffMonths < 1 -> {
                return " ${(diffDays - diffMonths / 30)} days ago "
            }
            diffHours > 0 && diffDays < 1 -> {
                return " ${(diffHours - diffDays * 24)} hours ago "
            }
            diffMin > 0 && diffHours < 1 -> {
                return " ${(diffMin - diffHours * 60)} min ago "
            }
            diffMin < 1 -> {
                return " just now"
            }
            else -> return ""
        }
    }

    interface OnClickListenerItem{
        fun onClick(article: Article)

    }

    interface OnShareNewUrl{
        fun onShare(article: Article)
    }

}