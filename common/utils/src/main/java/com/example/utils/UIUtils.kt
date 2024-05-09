package com.example.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}