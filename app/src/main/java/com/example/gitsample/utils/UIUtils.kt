package com.example.gitsample.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type
import kotlin.math.roundToInt

object UIUtils {

    private var pixelDensity: Float = 0.0f
    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    @JvmStatic
    fun init(context: Context) {
        screenHeight = context.resources.displayMetrics.heightPixels
        screenWidth = context.resources.displayMetrics.widthPixels
        pixelDensity = context.resources.displayMetrics.density

    }

    @JvmStatic
    fun dpToPx(dpValue: Float): Int {
        return (pixelDensity * dpValue).roundToInt()
    }

    @JvmStatic
    fun screenWidth(): Int {
        return screenWidth
    }

    @JvmStatic
    fun screenHeight(): Int {
        return screenHeight
    }

    @JvmStatic
    fun setFullScreenShow(activity: AppCompatActivity) {
        if (activity.window == null) {
            return
        }
        val controller =
            WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.hide(Type.navigationBars())
    }
}