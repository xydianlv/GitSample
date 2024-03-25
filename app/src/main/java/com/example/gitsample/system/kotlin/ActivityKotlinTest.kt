package com.example.gitsample.system.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityKotlinTestBinding
import com.example.gitsample.system.kotlin.coroutine.ActivityCoroutineTest
import com.example.gitsample.system.kotlin.tlfunction.ActivityTopLevelFunction
import com.example.gitsample.system.kotlin.viewmodel.ActivityViewModelTest
import com.example.gitsample.widget.list.CommonListItemData
import kotlinx.coroutines.Job

/**
 * Created by wyyu on 2024/1/24.
 **/
class ActivityKotlinTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityKotlinTest::class.java))
        }
    }

    private lateinit var binding: ActivityKotlinTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_TEST.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.KOTLIN_COROUTINE_TEST) {
            ActivityCoroutineTest.open(this)
        }).addItem(CommonListItemData.buildData(PageType.KOTLIN_VIEW_MODEL_TEST) {
            ActivityViewModelTest.open(this)
        }).addItem(CommonListItemData.buildData(PageType.KOTLIN_TOP_LEVEL_FUNCTION_TEST) {
            ActivityTopLevelFunction.open(this)
        }).refreshList()
    }
}