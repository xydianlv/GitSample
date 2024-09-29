package com.example.gitsample.widget.shadow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityShadowLayoutTestBinding
import com.example.widget.activity.BaseActivity

class ActivityShadowLayoutTest : BaseActivity<ActivityShadowLayoutTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShadowLayoutTest::class.java))
        }
    }

    override fun getViewBinding(): ActivityShadowLayoutTestBinding {
        return ActivityShadowLayoutTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SHADOW_LAYOUT.title)
    }
}