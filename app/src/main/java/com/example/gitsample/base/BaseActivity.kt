package com.example.gitsample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity() {
        checkStatusBar()
    }

    private fun checkStatusBar() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.isAppearanceLightStatusBars = isLightStatusBar()
    }

    /**
     * 设置状态栏展示模式，同时设置状态栏文字颜色
     *
     * true  - 日渐模式，文字为黑色
     * false - 暗黑模式，文字为白色
     */
    open fun isLightStatusBar(): Boolean {
        return true
    }
}