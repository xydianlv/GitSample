package com.example.gitsample.function.drag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityFloatDragBinding
import com.example.widget.activity.BaseActivity

class ActivityFloatDrag : BaseActivity<ActivityFloatDragBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFloatDrag::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
    }

    override fun getViewBinding(): ActivityFloatDragBinding {
        return ActivityFloatDragBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FLOAT_DRAG.title)
    }
}