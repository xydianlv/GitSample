package com.example.gitsample.function.applayout.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.widget.fragment.list.FragmentTestList

class FragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(p0: Int): Fragment {
        return FragmentTestList.getFragment()
    }
}