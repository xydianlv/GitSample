package com.example.gitsample.system.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.PageType
import com.example.gitsample.databinding.ActivitySystemInfoBinding
import com.example.widget.list.CommonListAdapter
import com.example.multi.cell.ClassCellManager
import com.example.widget.activity.BaseActivity

class ActivitySystemInfo : BaseActivity<ActivitySystemInfoBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySystemInfo::class.java))
        }
    }

    private var listAdapter: CommonListAdapter<Class<out Any>, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
        loadList()
    }

    override fun getViewBinding(): ActivitySystemInfoBinding {
        return ActivitySystemInfoBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SYSTEM_INFO.title)
    }

    private fun initList() {
        binding.apply {
            listAdapter = CommonListAdapter(ClassCellManager(), list)
            listAdapter!!.registerItem(SystemInfoData::class.java, SystemInfoHolder())

            list.adapter = listAdapter
            list.layoutManager = LinearLayoutManager(this@ActivitySystemInfo)
        }
    }

    private fun loadList() {
        SystemInfoUtils.loadSystemInfo(this) {
            listAdapter?.initList(it)
        }
    }
}