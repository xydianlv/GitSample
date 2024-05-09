package com.example.gitsample.system.kotlin.tlfunction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityTopLevelFunctionBinding
import com.example.widget.activity.BaseActivity

class ActivityTopLevelFunction : BaseActivity<ActivityTopLevelFunctionBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTopLevelFunction::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initValue()
    }

    override fun getViewBinding(): ActivityTopLevelFunctionBinding {
        return ActivityTopLevelFunctionBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_TOP_LEVEL_FUNCTION_TEST.title)
    }

    private fun initValue() {
        binding.info.text = appName()
    }
}