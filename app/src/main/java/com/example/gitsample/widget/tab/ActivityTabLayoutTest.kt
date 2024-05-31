package com.example.gitsample.widget.tab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityTabLayoutTestBinding
import com.example.resource.R
import com.example.widget.activity.BaseActivity
import com.google.android.material.tabs.TabLayout

class ActivityTabLayoutTest : BaseActivity<ActivityTabLayoutTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTabLayoutTest::class.java))
        }
    }

    override fun getViewBinding(): ActivityTabLayoutTestBinding {
        return ActivityTabLayoutTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initTabLayout()
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.TAB_LAYOUT.title)
    }

    private fun initTabLayout() {
        addTextTab(binding.tabLayout00, 5)
        addTextTab(binding.tabLayout01, 5)
        addViewTab(binding.tabLayout02, 5)
    }

    private fun addTextTab(tabLayout: TabLayout, count: Int) {
        for (index in 0 until count) {
            tabLayout.addTab(tabLayout.newTab().setText(resources.getText(R.string.text_short_cn)))
        }
    }

    private fun addViewTab(tabLayout: TabLayout, count: Int) {
        for (index in 0 until count) {
            val tab = tabLayout.newTab()
                .setCustomView(com.example.gitsample.R.layout.layout_tab_layout_item)
            tabLayout.addTab(tab)
        }
    }

}