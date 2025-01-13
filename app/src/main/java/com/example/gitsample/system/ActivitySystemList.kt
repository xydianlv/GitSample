package com.example.gitsample.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivitySystemListBinding
import com.example.gitsample.system.anr.ActivityAnrTest
import com.example.gitsample.system.file.ActivityFileManager
import com.example.gitsample.system.info.ActivitySystemInfo
import com.example.gitsample.system.jar.ActivityJarTest
import com.example.gitsample.system.kotlin.ActivityKotlinTest
import com.example.gitsample.system.permission.ActivityPermissionCheck
import com.example.gitsample.system.screen.ActivityScreenTest
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivitySystemList : BaseActivity<ActivitySystemListBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySystemList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivitySystemListBinding {
        return ActivitySystemListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SYSTEM.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.PERMISSION).clickListener {
            ActivityPermissionCheck.open(this)
        }).addItem(CommonListItemData.obj(PageType.FILE_MANAGER).clickListener {
            ActivityFileManager.open(this)
        }).addItem(CommonListItemData.obj(PageType.SCREEN_TEST).clickListener {
            ActivityScreenTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.SYSTEM_INFO).clickListener {
            ActivitySystemInfo.open(this)
        }).addItem(CommonListItemData.obj(PageType.KOTLIN_TEST).clickListener {
            ActivityKotlinTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.ANR_TEST).clickListener {
            ActivityAnrTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.JAR_TEST).clickListener {
            ActivityJarTest.open(this)
        }).refreshList()
    }
}