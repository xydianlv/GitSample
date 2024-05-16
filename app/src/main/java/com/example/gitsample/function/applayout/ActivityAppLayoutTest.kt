package com.example.gitsample.function.applayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.gitsample.databinding.ActivityAppLayoutTestBinding
import com.example.widget.activity.BaseActivity
import com.google.android.material.appbar.AppBarLayout

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
        initView()
    }

    override fun getViewBinding(): ActivityAppLayoutTestBinding {
        return ActivityAppLayoutTestBinding.inflate(layoutInflater)
    }

    private fun initViewPager() {
        binding.pager.adapter = FragmentPagerAdapter(this)
    }

    private fun initView() {
        binding.toTop.setOnClickListener {
            val layoutParams = binding.appBarLayout.layoutParams
            val behavior = if (layoutParams is CoordinatorLayout.LayoutParams) {
                layoutParams.behavior
            } else {
                null
            }
            if (behavior is AppBarLayout.Behavior) {
                behavior.topAndBottomOffset = -binding.appBarLayout.measuredHeight
            }
        }
    }
}