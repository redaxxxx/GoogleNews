package com.prof.reda.android.project.googlenews.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.ActivityHomeBinding
import com.prof.reda.android.project.googlenews.ui.fragments.BookmarkFragment
import com.prof.reda.android.project.googlenews.ui.fragments.NewsPaperFragment
import com.prof.reda.android.project.googlenews.ui.fragments.ProfileFragment
import com.prof.reda.android.project.googlenews.ui.fragments.SearchFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        var fragment: Fragment
        binding.bottomNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.newsHome ->{
                    fragment = NewsPaperFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.bookmark ->{
                    fragment = BookmarkFragment()
                    loadFragment(fragment)
                    true
                }

                else ->{
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    true
                }
            }

        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

    }
}