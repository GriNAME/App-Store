package com.example.details.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.details.presentation.ui.FeaturesFragment

class FeaturesPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeaturesFragment()
            1 -> FeaturesFragment()
            2 -> FeaturesFragment()
            else -> FeaturesFragment()
        }
    }
}