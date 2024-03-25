package com.example.gitsample.system.kotlin.tlfunction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityTopLevelFunctionBinding

class ActivityTopLevelFunction : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTopLevelFunction::class.java))
        }
    }

    private lateinit var binding: ActivityTopLevelFunctionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopLevelFunctionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initValue()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_TOP_LEVEL_FUNCTION_TEST.title)
    }

    private fun initValue() {
        binding.info.text = appName()
    }
}