package com.example.widget.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewbinding.ViewBinding
import com.example.utils.UIUtils

abstract class BaseActivity<vb : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: vb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initActivity()
    }

    protected abstract fun getViewBinding(): vb

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