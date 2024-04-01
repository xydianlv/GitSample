package com.example.gitsample.widget.text.span

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityTextSpanTestBinding
import com.example.gitsample.widget.list.CommonListAdapter
import com.example.multi.cell.ClassCellManager

class ActivityTextSpanTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTextSpanTest::class.java))
        }
    }

    private var adapter: CommonListAdapter<Class<out Any>, Any>? = null
    private var binding: ActivityTextSpanTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextSpanTestBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
        loadList()
    }

    private fun initToolbar() {
        binding?.toolbar?.initShow(PageType.TEXT_SPAN.title)
    }

    private fun initList() {
        binding?.apply {
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