package com.example.gitsample.canvas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.canvas.figure.CanvasFigureView
import com.example.gitsample.canvas.image.CanvasImageShadow
import com.example.gitsample.canvas.image.CanvasImageView
import com.example.gitsample.canvas.path.CanvasPathSwitchLayout
import com.example.gitsample.canvas.path.CanvasPathView
import com.example.gitsample.canvas.text.CanvasTextView
import com.example.gitsample.databinding.ActivityCanvasShowBinding
import com.example.widget.activity.BaseActivity

class ActivityCanvasShow : BaseActivity<ActivityCanvasShowBinding>() {

    companion object {

        private var pageType: PageType? = null

        @JvmStatic
        fun open(context: Context, pageType: PageType) {
            ActivityCanvasShow.pageType = pageType
            context.startActivity(Intent(context, ActivityCanvasShow::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initShow()
    }

    override fun getViewBinding(): ActivityCanvasShowBinding {
        return ActivityCanvasShowBinding.inflate(layoutInflater)
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

            PageType.CANVAS_IMAGE -> {
                CanvasImageView(this)
            }

            PageType.CANVAS_SHADOW -> {
                CanvasImageShadow(this)
            }

            PageType.CANVAS_PATH_SWITCH -> {
                CanvasPathSwitchLayout(this)
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