package com.example.gitsample.system.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivitySystemInfoBinding
import com.example.gitsample.widget.list.CommonListAdapter
import com.example.multi.cell.ClassCellManager

class ActivitySystemInfo : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySystemInfo::class.java))
        }
    }

    private var listAdapter: CommonListAdapter<Class<out Any>, Any>? = null

    private var binding: ActivitySystemInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemInfoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
        loadList()
    }

    private fun initToolbar() {
        binding?.toolbar?.initShow(PageType.SYSTEM_INFO.title)
    }

    private fun initList() {
        binding?.apply {
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