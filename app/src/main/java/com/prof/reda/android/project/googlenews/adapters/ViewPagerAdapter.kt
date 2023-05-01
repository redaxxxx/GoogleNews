package com.prof.reda.android.project.googlenews.adapters

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.prof.reda.android.project.googlenews.ui.fragments.BusinessFragment
import com.prof.reda.android.project.googlenews.ui.fragments.TechFragment
import com.prof.reda.android.project.googlenews.ui.fragments.TechnologyFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private lateinit var fragment:Fragment
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TechnologyFragment()
            1 -> BusinessFragment()
            else -> TechFragment()
        }
    }

}