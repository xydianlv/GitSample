package com.example.gitsample.utils

import android.content.Context
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
}