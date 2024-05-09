package com.example.gitsample.widget.text.span

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsample.databinding.ActivityTextSpanTestBinding
import com.example.widget.list.CommonListAdapter
import com.example.multi.cell.ClassCellManager
import com.example.widget.activity.BaseActivity

class ActivityTextSpanTest : BaseActivity<ActivityTextSpanTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTextSpanTest::class.java))
        }
    }

    private var adapter: CommonListAdapter<Class<out Any>, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
        loadList()
    }

    override fun getViewBinding(): ActivityTextSpanTestBinding {
        return ActivityTextSpanTestBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.TEXT_SPAN.title)
    }

    private fun initList() {
        binding.apply {
            adapter = CommonListAdapter(ClassCellManager(), list)
            adapter!!.registerItem(SpanInfoData::class.java, SpanInfoHolder())

            list.adapter = adapter
            list.layoutManager = LinearLayoutManager(this@ActivityTextSpanTest)
        }
    }

    private fun loadList() {
        TextSpanUtils.loadSpanTestList {
            adapter?.initList(it)
        }
    }
}