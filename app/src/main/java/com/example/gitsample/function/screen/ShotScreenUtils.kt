package com.example.gitsample.function.screen

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import com.example.gitsample.utils.ZLog
import com.example.gitsample.utils.ZToast
import java.io.File
import java.io.FileOutputStream

object ShotScreenUtils {

    @JvmStatic
    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

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
    fun saveBitmapToFile(bitmap: Bitmap, filePath: String) {
        if (filePath.isEmpty()) {
            ZToast.show("文件路径为空")
            return
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
        } ?: return
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutStream)
        try {
            fileOutStream.flush()
            fileOutStream.close()
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }
}