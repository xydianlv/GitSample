package com.example.gitsample.system.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityKotlinTestBinding
import com.example.gitsample.system.kotlin.coroutine.ActivityCoroutineTest
import com.example.gitsample.system.kotlin.tlfunction.ActivityTopLevelFunction
import com.example.gitsample.system.kotlin.viewmodel.ActivityViewModelTest
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

/**
 * Created by wyyu on 2024/1/24.
 **/
class ActivityKotlinTest : BaseActivity<ActivityKotlinTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityKotlinTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityKotlinTestBinding {
        return ActivityKotlinTestBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_TEST.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.KOTLIN_COROUTINE_TEST).clickListener {
            ActivityCoroutineTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.KOTLIN_VIEW_MODEL_TEST).clickListener {
            ActivityViewModelTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.KOTLIN_TOP_LEVEL_FUNCTION_TEST).clickListener {
            ActivityTopLevelFunction.open(this)
        }).refreshList()
    }
}