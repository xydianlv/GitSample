package com.example.gitsample.function.wallpaper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityWallpaperBinding
import com.example.gitsample.utils.UIUtils

class ActivityWallpaper : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityWallpaper::class.java))
        }
    }

    private lateinit var binding: ActivityWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UIUtils.setFullScreenShow(this)
        initActivity()
    }

    override fun isLightStatusBar(): Boolean {
        return false
    }

    private fun initActivity() {
        val clickListener = View.OnClickListener {

        }
        binding.btnLock.setOnClickListener(clickListener)
        binding.btnWall.setOnClickListener(clickListener)
    }
}