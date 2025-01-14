package com.example.gitsample.system.jar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.PageType
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityJarTestBinding
import com.example.gitsample.system.jar.utils.JarKtLoadUtils
import com.example.gitsample.system.jar.utils.JarLoadUtils
import com.example.gitsample.system.jar.utils.JarType
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
            when (it.id) {
                R.id.tv_toast -> {
                    showToast()
                }

                R.id.tv_load -> {
                    JarLoadUtils.loadJarFile(this, JarType.JAR_JAVA)
                }

                R.id.tv_load_dex -> {
                    JarLoadUtils.loadJarFile(this, JarType.JAR_JAVA_DEX)
                }

                R.id.tv_load_kt -> {
                    JarKtLoadUtils.loadJarFile(this, JarType.JAR_KOTLIN)
                }
            }
        }
        binding.tvToast.setOnClickListener(onClickListener)
        binding.tvLoad.setOnClickListener(onClickListener)
        binding.tvLoadDex.setOnClickListener(onClickListener)
        binding.tvLoadKt.setOnClickListener(onClickListener)
    }

    private fun showToast() {
        ZToast.show("ToastValue")
    }
}