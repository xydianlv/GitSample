package com.example.gitsample.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityWidgetListBinding
import com.example.gitsample.widget.dialog.ActivityMyDialogList
import com.example.gitsample.widget.list.CommonListItemData
import com.example.gitsample.widget.text.ActivityTextViewTest

class ActivityWidgetList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityWidgetList::class.java))
        }
    }

    private lateinit var binding: ActivityWidgetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWidgetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.WIDGET.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.DIALOG) {
            ActivityMyDialogList.open(this)
        }).addItem(CommonListItemData.buildData(PageType.TEXT_VIEW) {
            ActivityTextViewTest.open(this)
        }).refreshList()
    }
}