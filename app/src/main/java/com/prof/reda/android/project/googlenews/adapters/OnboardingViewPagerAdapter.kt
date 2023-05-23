package com.prof.reda.android.project.googlenews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.OnboardingViewPagerBinding

class OnboardingViewPagerAdapter(val context:Context) : PagerAdapter() {

    private lateinit var inflater:LayoutInflater
    private lateinit var binding: OnboardingViewPagerBinding

    private val images = arrayOf(R.drawable.news_img,
    R.drawable.news_img2)

    private val desc = arrayOf("An app to freely share your real life events and attain genuine followers",
    "A locus where you get a chance to authenticate new to make a impact on society")

    override fun getCount(): Int {
        return desc.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.onboarding_view_pager, container,false)

        //init views
        binding.onboardingImgView.setImageResource(images[position])
        binding.descTextView.text = desc[position]

        container.addView(binding.root)
        return binding.root
    }
}