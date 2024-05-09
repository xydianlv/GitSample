package com.example.gitsample.system.kotlin.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityViewModelTestBinding
import com.example.widget.activity.BaseVMActivity
import java.lang.StringBuilder

class ActivityViewModelTest : BaseVMActivity<ViewModelTest, ActivityViewModelTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityViewModelTest::class.java))
        }
    }

    private val viewModelA: ViewModelTest by lazy {
        ViewModelProvider(this)[ViewModelTest::class.java]
    }

    // 不能在 onCreate 之前，通过 ViewModelProvider 获取 ViewModel 实例对象，会报错直接崩掉
    // private val viewModelB = ViewModelProvider(this)[ViewModelTest::class.java]
    private var viewModelB: ViewModelTest? = null

    // 收集加载结果
    private val infoMap: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initViewModel()
        loadValue()
    }

    override fun getViewBinding(): ActivityViewModelTestBinding {
        return ActivityViewModelTestBinding.inflate(layoutInflater)
    }

    override fun getVMClass(): Class<ViewModelTest> {
        return ViewModelTest::class.java
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_VIEW_MODEL_TEST.title)
    }

    private fun initViewModel() {
        viewModelB = ViewModelProvider(this)[ViewModelTest::class.java]
    }

    private fun loadValue() {
        viewModelA.run("info from viewModelA") {
            infoMap["viewModelA"] = it
            checkShow()
        }
        viewModelB?.run("info from viewModelB") {
            infoMap["viewModelB"] = it
            checkShow()
        }
        viewModel.run("info from viewModel") {
            infoMap["viewModel"] = it
            checkShow()
        }
    }

    private fun checkShow() {
        if (infoMap.isEmpty()) {
            return
        }
        val strBuilder = StringBuilder()
        infoMap.forEach { (key, value) ->
            strBuilder.append(key).append(" : ").append(value).append("\n")
        }
        binding.info.text = strBuilder
    }
}