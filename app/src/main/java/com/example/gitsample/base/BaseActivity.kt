package com.example.gitsample.base

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.gitsample.utils.UIUtils

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity() {
        checkStatusBar()
        checkFullscreen()
    }

    private fun checkStatusBar() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.isAppearanceLightStatusBars = isLightStatusBar()
    }

    private fun checkFullscreen() {
        if (isFullScreen()) {
            UIUtils.setFullScreenShow(this)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 设置状态栏展示模式，同时设置状态栏文字颜色
     *
     * true  - 日间模式，文字为黑色
     * false - 暗黑模式，文字为白色
     */
    open fun isLightStatusBar(): Boolean {
        return true
    }

    /**
     * 是否全屏展示
     * true  - 全屏展示
     * false - 非全屏展示
     */
    open fun isFullScreen(): Boolean {
        return false
    }
}