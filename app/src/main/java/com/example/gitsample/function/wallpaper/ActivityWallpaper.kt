package com.example.gitsample.function.wallpaper

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityWallpaperBinding
import com.example.gitsample.system.permission.PermissionChecker
import com.example.gitsample.system.permission.PermissionItemType
import com.example.gitsample.system.permission.PermissionRequestCallback
import com.example.utils.ZLog
import com.example.widget.view.ZToast
import com.example.utils.BitmapUtils
import com.example.utils.UIUtils
import com.example.widget.activity.BaseActivity

class ActivityWallpaper : BaseActivity<ActivityWallpaperBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityWallpaper::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIUtils.setFullScreenShow(this)
        initActivity()
    }

    override fun getViewBinding(): ActivityWallpaperBinding {
        return ActivityWallpaperBinding.inflate(layoutInflater)
    }

    override fun isLightStatusBar(): Boolean {
        return false
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    private fun initActivity() {
        val clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btn_lock -> {
                    checkPermission("授权后才可设置锁屏") { setWallpaper(0) }
                }
                R.id.btn_wall -> {
                    checkPermission("授权后才可设置壁纸") { setWallpaper(1) }
                }
            }
        }
        binding.btnLock.setOnClickListener(clickListener)
        binding.btnWall.setOnClickListener(clickListener)
    }

    private fun checkPermission(errorInfo: String, onGranted: () -> Unit) {
        PermissionChecker.requestPermission(PermissionItemType.WALLPAPER,
            object : PermissionRequestCallback {
                override fun onDenied() {
                    ZToast.show(errorInfo)
                }

                override fun onGranted() {
                    onGranted.invoke()
                }
            })
    }

    private fun setWallpaper(type: Int) {
        try {
            BitmapUtils.getBitmapFromView(binding.image) {
                if (it == null) {
                    ZToast.show("Bitmap 获取失败")
                }
                val manager = WallpaperManager.getInstance(this)
                if (type == 0) {
                    manager.setBitmap(it, null, true, WallpaperManager.FLAG_LOCK)
                    ZToast.show("锁屏设置成功")
                } else if (type == 1) {
                    manager.setBitmap(it)
                    ZToast.show("壁纸设置成功")
                }
            }
        } catch (e: Exception) {
            ZLog.e(e)
            if (type == 0) {
                ZToast.show("锁屏设置失败")
            } else if (type == 1) {
                ZToast.show("壁纸设置失败")
            }
        }
    }
}