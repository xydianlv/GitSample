package com.example.gitsample.system.jar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.PageType
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityJarTestBinding
import com.example.gitsample.system.jar.utils.JarLoadUtils
import com.example.widget.activity.BaseActivity
import com.example.widget.view.ZToast

class ActivityJarTest : BaseActivity<ActivityJarTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityJarTest::class.java))
        }
    }

    override fun getViewBinding(): ActivityJarTestBinding {
        return ActivityJarTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initClick()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.JAR_TEST.title)
    }

    private fun initClick() {
        val onClickListener = View.OnClickListener {
            if (it.id == R.id.tv_toast) {
                showToast()
            } else if (it.id == R.id.tv_load) {
                loadJar()
            }
        }
        binding.tvToast.setOnClickListener(onClickListener)
        binding.tvLoad.setOnClickListener(onClickListener)
    }

    private fun showToast() {
        ZToast.show("ToastValue")
    }

    private fun loadJar() {
        JarLoadUtils.loadJarFile(this)
    }
}