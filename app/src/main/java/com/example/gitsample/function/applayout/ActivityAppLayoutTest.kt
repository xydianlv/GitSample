package com.example.gitsample.function.applayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityAppLayoutTestBinding
import com.example.gitsample.function.applayout.fragment.FragmentPagerAdapter
import java.lang.StringBuilder

class ActivityAppLayoutTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAppLayoutTest::class.java))
        }
    }

    private lateinit var binding: ActivityAppLayoutTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppLayoutTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        binding.pager.adapter = FragmentPagerAdapter(this)
    }
}