package com.example.gitsample.widget.text

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityTextViewTestBinding
import com.example.gitsample.widget.text.span.ActivityTextSpanTest
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityTextViewTest : BaseActivity<ActivityTextViewTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTextViewTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityTextViewTestBinding {
        return ActivityTextViewTestBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.TEXT_VIEW.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.TEXT_SPAN).clickListener {
            ActivityTextSpanTest.open(this@ActivityTextViewTest)
        }).refreshList()
    }
}