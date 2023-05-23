package com.prof.reda.android.project.googlenews.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.adapters.OnboardingViewPagerAdapter
import com.prof.reda.android.project.googlenews.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOnboardingBinding
    private lateinit var viewPagerAdapter:OnboardingViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        initialize()
    }

    private fun initialize(){
        viewPagerAdapter = OnboardingViewPagerAdapter(this)

        //create listener
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0){
                    binding.skipBtn.visibility = View.VISIBLE
                    binding.onboardingBtn.text = "Next"
                }else{
                    binding.skipBtn.visibility = View.GONE
                    binding.onboardingBtn.text = "Get Started"
                }
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        binding.viewPager.adapter = viewPagerAdapter

        binding.onboardingBtn.setOnClickListener{
            if (binding.onboardingBtn.text.toString().equals("Next")){
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }else{
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }

        binding.skipBtn.setOnClickListener{
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
    }

}