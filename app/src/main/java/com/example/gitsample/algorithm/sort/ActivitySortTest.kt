package com.example.gitsample.algorithm.sort

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.PageType
import com.example.gitsample.databinding.ActivitySortTestBinding
import com.example.multi.cell.ClassCellManager
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListAdapter

/**
 * Created by wyyu on 2024/1/28.
 **/
class ActivitySortTest : BaseActivity<ActivitySortTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySortTest::class.java))
        }

        private const val TEST_VALUE = "10 8 90 235 10 76 5 87 467 8"
    }

    private var listAdapter: CommonListAdapter<Class<out Any>, Any>? = null
    private val sortList = ArrayList<SortItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initList()
        loadList()
    }

    override fun getViewBinding(): ActivitySortTestBinding {
        return ActivitySortTestBinding.inflate(layoutInflater)
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.SORT.title)

        binding.sort.setOnClickListener {
            val inputValue = binding.input.text
            tryBuildIntArray(inputValue.toString())
        }
        binding.clear.setOnClickListener {
            binding.input.setText("")
        }
        binding.input.addTextChangedListener {
            binding.alert.text = ""
            binding.clear.visibility = if (it.isNullOrEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
        binding.input.setText(TEST_VALUE)
    }

    private fun tryBuildIntArray(strValue: String) {
        val strBuilder: StringBuilder = StringBuilder()
        val valueList = ArrayList<Int>()
        for (charValue in strValue.toCharArray()) {
            if (charValue in '0'..'9') {
                strBuilder.append(charValue)
            } else if (strBuilder.isNotEmpty() && strBuilder.isNotBlank()) {
                valueList.add(strBuilder.toString().toInt())
                strBuilder.clear()
            }
        }
        if (strBuilder.isNotEmpty() && strBuilder.isNotBlank()) {
            valueList.add(strBuilder.toString().toInt())
        }
        if (valueList.isEmpty()) {
            binding.alert.text = "请按照输入规范输入待排序数字列表"
        } else {
            trySort(valueList)
        }
    }

    private fun trySort(valueList: ArrayList<Int>) {
        sortList.forEachIndexed { index, sortItemData ->
            SortUtils.sortArray(sortItemData.sortType, valueList.toTypedArray()) { time, result ->
                sortItemData.time = time
                sortItemData.resultList.clear()
                sortItemData.resultList.addAll(result)
                listAdapter?.updateItem(index, sortItemData)
            }
        }
    }

    private fun initList() {
        listAdapter = CommonListAdapter(ClassCellManager(), binding.list)
        listAdapter!!.registerItem(SortItemData::class.java, SortItemHolder())

        binding.list.adapter = listAdapter
        binding.list.layoutManager = LinearLayoutManager(this@ActivitySortTest)
    }

    private fun loadList() {
        sortList.addAll(SortItemData.buildSortList())
        listAdapter?.initList(sortList)
    }
}