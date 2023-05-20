package com.prof.reda.android.project.googlenews.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.ActivityMainBinding
import com.prof.reda.android.project.googlenews.ui.fragments.FavoriteFragment
import com.prof.reda.android.project.googlenews.ui.fragments.NewsPaperFragment
import com.prof.reda.android.project.googlenews.ui.fragments.ProfileFragment
import com.prof.reda.android.project.googlenews.ui.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var fragment:Fragment
        binding.bottomNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.newsHome ->{
                    fragment = NewsPaperFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.favorite ->{
                    fragment = FavoriteFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.profile ->{
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.search ->{
                    fragment = SearchFragment()
                    loadFragment(fragment)
                    true
                }
                else -> {false}
            }

        }

    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

    }
}