package com.example.gitsample.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivitySystemListBinding
import com.example.gitsample.system.file.ActivityFileManager
import com.example.gitsample.system.info.ActivitySystemInfo
import com.example.gitsample.system.kotlin.ActivityKotlinTest
import com.example.gitsample.system.permission.ActivityPermissionCheck
import com.example.gitsample.system.screen.ActivityScreenTest
import com.example.gitsample.widget.list.CommonListItemData

class ActivitySystemList : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySystemList::class.java))
        }
    }

    private lateinit var binding: ActivitySystemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SYSTEM.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.PERMISSION) {
            ActivityPermissionCheck.open(this)
        }).addItem(CommonListItemData.buildData(PageType.FILE_MANAGER) {
            ActivityFileManager.open(this)
        }).addItem(CommonListItemData.buildData(PageType.SCREEN_TEST) {
            ActivityScreenTest.open(this)
        }).addItem(CommonListItemData.buildData(PageType.SYSTEM_INFO) {
            ActivitySystemInfo.open(this)
        }).addItem(CommonListItemData.buildData(PageType.KOTLIN_TEST) {
            ActivityKotlinTest.open(this)
        }).refreshList()
    }
}