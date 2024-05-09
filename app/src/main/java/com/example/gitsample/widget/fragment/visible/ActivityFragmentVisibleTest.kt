package com.example.gitsample.widget.fragment.visible

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityFragmentVisibleTestBinding
import com.example.gitsample.widget.fragment.frag.FragmentPagerAdapter
import com.example.widget.activity.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivityFragmentVisibleTest : BaseActivity<ActivityFragmentVisibleTestBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFragmentVisibleTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initPager()
    }

    override fun getViewBinding(): ActivityFragmentVisibleTestBinding {
        return ActivityFragmentVisibleTestBinding.inflate(layoutInflater)
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.FRAGMENT_VISIBLE_TEST.title)
    }

    private fun initPager() {
        binding.pager.adapter = FragmentPagerAdapter(this)
        binding.pager.offscreenPageLimit = 2
        binding.pager.currentItem = 0

        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = "Tab$position"
            tab.id = position
        }.attach()
    }
}