package com.example.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.resource.R

object ColorUtils {

    private val PLACE_HOLDER_COLOR_ARRAY = arrayOf(
        R.color.color_holder_66CF8888,
        R.color.color_holder_66B7D28D,
        R.color.color_holder_FFE7DAC9,
        R.color.color_holder_66F3D64E
    )

    private var colorIndex: Int = 0

    @ColorInt
    @JvmStatic
    fun placeHolderColor(context: Context): Int {
        colorIndex++
        return ContextCompat.getColor(
            context, PLACE_HOLDER_COLOR_ARRAY[colorIndex % PLACE_HOLDER_COLOR_ARRAY.size]
        )
    }
}