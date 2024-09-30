package com.example.gitsample.widget.shadow

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.ColorInt

class VShadowHelper(mViewGroup: ViewGroup) {

    private val viewGroup: ViewGroup = mViewGroup

    private var shadowAlpha: Float = 0.0f
    private var viewRadius: Float = 0.0f

    fun setShadowColor(@ColorInt colorValue: Int) {
        viewGroup.outlineAmbientShadowColor = colorValue
        viewGroup.outlineSpotShadowColor = colorValue
        viewGroup.invalidateOutline()
    }

    fun setShadowElevation(elevation: Float) {
        viewGroup.elevation = elevation
    }

    fun setShadowAlpha(alpha: Float) {
        shadowAlpha = alpha
        refreshRadiusAndAlpha()
    }

    fun setRadius(radius: Float) {
        viewRadius = radius
        refreshRadiusAndAlpha()
    }

    private fun refreshRadiusAndAlpha() {
        viewGroup.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                if (view == null || outline == null) {
                    return
                }
                outline.alpha = shadowAlpha
                if (viewRadius <= 0.0f) {
                    outline.setRect(0, 0, view.width, view.height)
                } else {
                    outline.setRoundRect(0, 0, view.width, view.height, viewRadius)
                }
            }
        }
        viewGroup.clipToOutline = true
    }
}