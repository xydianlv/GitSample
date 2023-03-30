package com.example.gitsample.function.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityShotScreenBinding
import com.example.gitsample.system.file.FileType
import com.example.gitsample.system.file.PathManager
import com.example.gitsample.utils.ZToast

class ActivityShotScreen : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShotScreen::class.java))
        }
    }

    private lateinit var binding: ActivityShotScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShotScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initClick()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SHOT_SCREEN.title)
    }

    private fun initClick() {
        val clickListener = View.OnClickListener {
            when (it.id) {
                R.id.click_image -> {
                    saveImage()
                }
                R.id.click_scroll -> {
                    saveScroll()
                }
            }
        }
        binding.clickImage.setOnClickListener(clickListener)
        binding.clickScroll.setOnClickListener(clickListener)
    }

    private fun saveImage() {
        ShotScreenUtils.getBitmapFromView(binding.image) {
            if (it == null) {
                ZToast.show("获取 Bitmap 失败")
            } else {
                val fileName = "ShotScreen-" + System.currentTimeMillis() + ".jpeg"
                val filePath = PathManager.manager().saveMediaPath() + fileName
                ShotScreenUtils.saveBitmapToFile(it, filePath)
            }
        }
    }

    private fun saveScroll() {

    }
}