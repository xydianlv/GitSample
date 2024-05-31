package com.example.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.resource.R

object ColorUtils {

    private val PLACE_HOLDER_COLOR_ARRAY = arrayOf(R.color.color_holder_66CF8888)

    @JvmStatic
    fun placeHolderColor(context: Context) {
        ContextCompat.getColor(context, PLACE_HOLDER_COLOR_ARRAY[0])
    }
}