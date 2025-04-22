package com.example.gitsample.system.notify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityNotifyCheckBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityNotifyCheck : BaseActivity<ActivityNotifyCheckBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityNotifyCheck::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initChannel()
        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityNotifyCheckBinding {
        return ActivityNotifyCheckBinding.inflate(layoutInflater)
    }

    private fun initChannel() {
        NotifyTestUtils.tryInitNotifyChannel(this)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.NOTIFY_TEST.title)
    }

    private fun initList() {
        for (type in NotifyTestType.entries) {
            val itemData = CommonListItemData.obj(type.title, type.info).clickListener {
                NotifyTestUtils.trySendNotify(this, type)
            }
            binding.list.addItem(itemData)
        }
        binding.list.refreshList()
    }
}