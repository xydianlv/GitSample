package com.example.gitsample.function.appwidget.provider

import android.content.ComponentName
import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.example.gitsample.R
import com.example.gitsample.function.appwidget.api.JsonKunWidget

data class KunWidgetParams(
    val context: Context,
    val jsonKunWidget: JsonKunWidget,
    val componentName: ComponentName,
    @LayoutRes val layout: Int,
    val callback: OnResCallback
) {
    companion object {
        val LAYOUT_BUBBLE = R.layout.layout_kun_bubble_0_0
    }
}

interface OnResCallback {

    fun resCount(): Int

    @IdRes
    fun idResLayout(index: Int): Int

    @IdRes
    fun idResHolder(index: Int): Int

    @LayoutRes
    fun layoutResBubble(index: Int): Int
}