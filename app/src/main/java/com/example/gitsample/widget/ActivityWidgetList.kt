package com.example.gitsample.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityShowListBinding
import com.example.gitsample.widget.dialog.ActivityMyDialogList
import com.example.gitsample.widget.list.CommonListItemData

class ActivityWidgetList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityWidgetList::class.java))
        }
    }

    private lateinit var binding: ActivityShowListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowListBinding.inflate(layoutInflater)

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
        }).refreshList()
    }
}