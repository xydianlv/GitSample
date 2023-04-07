package com.example.gitsample.canvas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.canvas.figure.CanvasFigureView
import com.example.gitsample.canvas.path.CanvasPathView
import com.example.gitsample.canvas.text.CanvasTextView
import com.example.gitsample.databinding.ActivityCanvasShowBinding

class ActivityCanvasShow : BaseActivity() {

    companion object {

        private var pageType: PageType? = null

        @JvmStatic
        fun open(context: Context, pageType: PageType) {
            ActivityCanvasShow.pageType = pageType
            context.startActivity(Intent(context, ActivityCanvasShow::class.java))
        }
    }

    private lateinit var binding: ActivityCanvasShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanvasShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initShow()
    }

    private fun initToolbar() {
        val title = if (pageType == null) {
            ""
        } else {
            pageType!!.title
        }
        binding.toolbar.initShow(title)
    }

    private fun initShow() {
        val view = when (pageType) {
            PageType.CANVAS_FIGURE -> {
                CanvasFigureView(this)
            }
            PageType.CANVAS_TEXT -> {
                CanvasTextView(this)
            }
            PageType.CANVAS_PATH -> {
                CanvasPathView(this)
            }
            else -> {
                null
            }
        }
        if (view != null) {
            binding.container.removeAllViews()
            binding.container.addView(view)
        }
    }
}