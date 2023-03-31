package com.example.gitsample.system.screen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gitsample.R
import com.example.gitsample.databinding.ActionNoActionBarBinding

class ActivityNoActionBar : AppCompatActivity() {

    companion object {

        @JvmField
        var screenType: ScreenType? = null

        @JvmStatic
        fun open(context: Context, type: ScreenType) {
            screenType = type
            context.startActivity(Intent(context, ActivityNoActionBar::class.java))
        }
    }

    private lateinit var binding: ActionNoActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActionNoActionBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initInfoShow()
        initScreenShow()
    }

    private fun initInfoShow() {
        binding.title.text = if (screenType == null) {
            "ScreenType is null"
        } else {
            screenType!!.title
        }
        binding.info.text = if (screenType == null) {
            "ScreenType is null"
        } else {
            screenType!!.info
        }
    }

    private fun initScreenShow() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        when (screenType) {
            ScreenType.NO_ACTION_BAR -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
            ScreenType.NO_ACTION_BAR_HIDE_STATUS -> {
                controller.hide(WindowInsetsCompat.Type.statusBars())
            }
            ScreenType.NO_ACTION_BAR_TRANSPARENT_STATUS -> {
                window.statusBarColor = Color.TRANSPARENT
            }
            ScreenType.NO_ACTION_BAR_APPOINT_COLOR_STATUS -> {
                // 若要自定义状态栏色值，理论上需要加上这个 Flags，但是目前使用发现不需要
                // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, R.color.color_gray)
            }
            ScreenType.NO_ACTION_FULLSCREEN -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            ScreenType.NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.statusBarColor = Color.TRANSPARENT
            }
            else -> {

            }
        }
    }
}