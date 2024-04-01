package com.example.gitsample.widget.text.span

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityTextSpanTestBinding

class ActivityTextSpanTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTextSpanTest::class.java))
        }
    }

    private lateinit var binding: ActivityTextSpanTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextSpanTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.TEXT_SPAN.title)
    }
}