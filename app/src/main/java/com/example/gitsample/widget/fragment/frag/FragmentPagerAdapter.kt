package com.example.gitsample.widget.fragment.frag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.widget.fragment.text.FragmentTestText

class FragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(p0: Int): Fragment {
        return FragmentTestText.fragment(p0.toString())
    }
}