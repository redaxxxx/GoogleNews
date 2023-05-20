package com.prof.reda.android.project.googlenews.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.MediaColumns.AUTHOR
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.prof.reda.android.project.googlenews.*
import com.prof.reda.android.project.googlenews.databinding.ActivityNewsDetailsBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class NewsDetails : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    private lateinit var urlToImg:String
    private lateinit var publishedAt:String
    private lateinit var title:String
    private lateinit var name:String
    private lateinit var description:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)

        getInfoFromIntent()

        Picasso.with(this).load(urlToImg).into(binding.newsImgView)
        binding.publishedAtTv.text = formatDateAsAgo(publishedAt)
        binding.titleTv.text = title
        binding.nameTv.text = name
        binding.description.text = description
    }

    private fun getInfoFromIntent(){
        val intent:Intent = intent
        urlToImg = intent.getStringExtra(URL_IMAGE).toString()
        publishedAt = intent.getStringExtra(PUBLISHED_AT).toString()
        title = intent.getStringExtra(TITLE).toString()
        name = intent.getStringExtra(NEWS_NAME).toString()
        description = intent.getStringExtra(DESCRIPTION).toString()
        Log.d(TAG, "url to image: $urlToImg")
        Log.d(TAG, "published at: $publishedAt")
        Log.d(TAG, "title : $title")
        Log.d(TAG, "name: $name")
    }

    private fun formatDateAsAgo(format: String): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val result = simpleDateFormat.parse(format)

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
}