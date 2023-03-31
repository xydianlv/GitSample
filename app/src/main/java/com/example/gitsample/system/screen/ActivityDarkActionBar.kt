package com.example.gitsample.system.screen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type
import com.example.gitsample.databinding.ActivityDarkActionBarBinding

class ActivityDarkActionBar : AppCompatActivity() {

    companion object {

        @JvmField
        var screenType: ScreenType? = null

        @JvmStatic
        fun open(context: Context, type: ScreenType) {
            screenType = type
            context.startActivity(Intent(context, ActivityDarkActionBar::class.java))
        }
    }

    private lateinit var binding: ActivityDarkActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkActionBarBinding.inflate(layoutInflater)
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
            ScreenType.DARK_ACTION_BAR_HIDE_STATUS -> {
                controller.hide(Type.statusBars())
            }
            ScreenType.DARK_ACTION_BAR_TRANSPARENT_STATUS -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
            ScreenType.DARK_ACTION_BAR_HIDE_NAVIGATION_BAR -> {
                controller.hide(Type.navigationBars())
            }
            ScreenType.DARK_ACTION_BAR_HIDE_SYSTEM_BAR -> {
                controller.hide(Type.systemBars())
            }
            ScreenType.DARK_ACTION_BAR_FULLSCREEN -> {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
            else -> {

            }
        }
    }
}