package com.example.gitsample.widget.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityShowListBinding
import com.example.gitsample.widget.list.CommonListItemData

class ActivityMyDialogList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityMyDialogList::class.java))
        }
    }

    private lateinit var binding: ActivityShowListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.DIALOG.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData("AlertDialog", "提示型Dialog") {

        }).addItem(CommonListItemData.buildData("FullScreenDialog", "全屏Dialog") {

        }).addItem(CommonListItemData.buildData("InputDialog", "带输入框的Dialog") {

        }).refreshList()
    }
}