package com.example.gitsample.function.screen

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.example.gitsample.R
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityShotScreenBinding
import com.example.gitsample.system.file.PathManager
import com.example.widget.view.ZToast
import com.example.utils.BitmapUtils
import com.example.utils.MediaUtils
import com.example.widget.activity.BaseActivity

class ActivityShotScreen : BaseActivity<ActivityShotScreenBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShotScreen::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initClick()
    }

    override fun getViewBinding(): ActivityShotScreenBinding {
        return ActivityShotScreenBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SHOT_SCREEN.title)
    }

    private fun initClick() {
        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.click_image -> {
                    BitmapUtils.getBitmapFromView(binding.image) { onLoadBitmapFinish(it) }
                }
                R.id.click_scroll -> {
                    BitmapUtils.getBitmapFromScroll(binding.scroll) { onLoadBitmapFinish(it) }
                }
            }
        }
        binding.clickImage.setOnClickListener(clickListener)
        binding.clickScroll.setOnClickListener(clickListener)
    }

    private fun onLoadBitmapFinish(bitmap: Bitmap?) {
        if (bitmap == null) {
            ZToast.show("获取 Bitmap 失败")
            return
        }
        val fileName = "ShotScreen-" + System.currentTimeMillis() + ".jpeg"
        val filePath = PathManager.manager().saveMediaPath() + fileName
        if (BitmapUtils.saveBitmapToFile(bitmap, filePath)) {
            // 获取到的是 SD 卡上的私有目录，该目录无法被 MediaStore 扫描到，需要再复制到 DCIM 目录下
            MediaUtils.saveMediaToAlbum(filePath, fileName)
            ZToast.show("图片已保存到本地相册")
        } else {
            ZToast.show("截图失败")
        }
        bitmap.recycle()
    }
}