package com.example.gitsample.widget.text

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityTextViewTestBinding
import com.example.gitsample.widget.list.CommonListItemData
import com.example.gitsample.widget.text.span.ActivityTextSpanTest

class ActivityTextViewTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTextViewTest::class.java))
        }
    }

    private lateinit var binding: ActivityTextViewTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.TEXT_VIEW.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.TEXT_SPAN) {
            ActivityTextSpanTest.open(this@ActivityTextViewTest)
        }).refreshList()
    }
}