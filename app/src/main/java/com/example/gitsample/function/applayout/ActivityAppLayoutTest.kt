package com.example.gitsample.function.applayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.databinding.ActivityAppLayoutTestBinding
import com.example.widget.activity.BaseActivity

class ActivityAppLayoutTest : BaseActivity<ActivityAppLayoutTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAppLayoutTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
    }

    override fun getViewBinding(): ActivityAppLayoutTestBinding {
        return ActivityAppLayoutTestBinding.inflate(layoutInflater)
    }

    private fun initViewPager() {
        binding.pager.adapter = FragmentPagerAdapter(this)
    }
}