package com.example.gitsample.function.drag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityFloatDragBinding

class ActivityFloatDrag : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFloatDrag::class.java))
        }
    }

    private lateinit var binding: ActivityFloatDragBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatDragBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FLOAT_DRAG.title)
    }
}