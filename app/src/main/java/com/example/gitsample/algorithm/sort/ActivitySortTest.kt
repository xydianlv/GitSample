package com.example.gitsample.algorithm.sort

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivitySortTestBinding

/**
 * Created by wyyu on 2024/1/28.
 **/
class ActivitySortTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySortTest::class.java))
        }
    }

    private lateinit var binding: ActivitySortTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initView()
        initItemShow()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SORT.title)
    }

    private fun initView() {
        binding.sort.setOnClickListener {
            val inputValue = binding.input.text
            tryBuildIntArray(inputValue.toString())
        }
        binding.input.addTextChangedListener {
            binding.alert.text = ""
        }
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
        SortUtils.sortPop(valueList.toTypedArray()) { time, result ->
            binding.pop.refreshResult(result, time)
        }
        SortUtils.sortInsert(valueList.toTypedArray()) { time, result ->
            binding.insert.refreshResult(result, time)
        }
        SortUtils.sortSelect(valueList.toTypedArray()) { time, result ->
            binding.select.refreshResult(result, time)
        }
    }

    private fun initItemShow() {
        binding.pop.initTitle("PopSort")
        binding.insert.initTitle("InsertSort")
        binding.select.initTitle("SelectSort")
    }
}