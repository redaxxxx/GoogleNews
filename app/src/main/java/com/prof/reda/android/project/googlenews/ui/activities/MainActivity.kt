package com.prof.reda.android.project.googlenews.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Handler().postDelayed(Runnable {
            kotlin.run { isFirstTime() }
        }, 1500)
    }

    private fun isFirstTime() {
        //for checking if the app is running for the very first time
        //we need to saved a value to shared preferences
        val preferences = getSharedPreferences("onBoard", MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean("isFirstTime", true)

        //default value
        if (isFirstTime) {
            //if its true then its first time and we will change it false
            val editor = preferences.edit()
            editor.putBoolean("isFirstTime", false)
            editor.apply()
            startActivity(Intent(this, OnboardingActivity::class.java))
        } else {
            //Start Home
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}