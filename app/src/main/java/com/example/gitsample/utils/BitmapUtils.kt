package com.example.gitsample.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

object BitmapUtils {

    @JvmStatic
    fun getBitmapFromView(view: View, call: (Bitmap?) -> Unit) {
        val activity = view.context
        if (activity !is Activity || activity.window == null) {
            call.invoke(null)
            return
        }
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val location = IntArray(2)
        view.getLocationInWindow(location)
        try {
            // PixelCopy 会将屏幕上，指定区域内的所有控件都截取下来，生成Bitmap，类似于截屏
            // Canvas 会将指定控件的展示内容，绘制在 Canvas 上，生成 Bitmap，使用 Canvas 截图，绘制的控件应当加上背景色，否则背景透明
            // 若两个 View 叠加到一起，但是需要下面指定 View 的展示内容，应当用 Canvas 来截屏
            PixelCopy.request(
                activity.window,
                Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
                bitmap,
                {
                    if (it == PixelCopy.SUCCESS) {
                        call.invoke(bitmap)
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } catch (e: Exception) {
            call.invoke(null)
            ZLog.e(e)
        }
    }

    @JvmStatic
    fun getBitmapFromScroll(view: ScrollView, call: (Bitmap?) -> Unit) {
        val activity = view.context
        if (activity !is AppCompatActivity || activity.window == null) {
            return call.invoke(null)
        }
        var viewHeight = 0
        for (index in 0 until view.childCount) {
            val child = view.getChildAt(index)
            viewHeight += child.height
        }
        activity.lifecycleScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                val bitmap = Bitmap.createBitmap(view.width, viewHeight, Bitmap.Config.ARGB_8888)
                // 通过 Canvas 绘制的 Bitmap，View 必须要有背景色
                view.draw(Canvas(bitmap))
                bitmap
            }
            call.invoke(bitmap)
        }
    }

    @JvmStatic
    fun saveBitmapToFile(bitmap: Bitmap, filePath: String): Boolean {
        if (filePath.isEmpty()) {
            ZLog.e("filePath isEmpty")
            return false
        }
        val file = File(filePath)
        try {
            file.createNewFile()
        } catch (e: Exception) {
            ZLog.e(e)
        }
        val fileOutStream = try {
            FileOutputStream(file)
        } catch (e: Exception) {
            null
        }
        if (fileOutStream == null) {
            ZLog.e("fileOutStream is null")
            return false
        }
        return try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutStream)
            fileOutStream.flush()
            fileOutStream.close()
            true
        } catch (e: Exception) {
            ZLog.e(e)
            false
        }
    }
}