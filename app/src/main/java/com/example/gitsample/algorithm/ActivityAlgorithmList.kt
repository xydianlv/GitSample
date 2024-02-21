package com.example.gitsample.algorithm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.algorithm.link.ActivityLinkTest
import com.example.gitsample.algorithm.sort.ActivitySortTest
import com.example.gitsample.algorithm.traversal.ActivityTraversalTest
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityAlgorithmListBinding
import com.example.gitsample.widget.list.CommonListItemData

/**
 * Created by wyyu on 2024/1/28.
 **/
class ActivityAlgorithmList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAlgorithmList::class.java))
        }
    }

    private lateinit var binding: ActivityAlgorithmListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlgorithmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.ALGORITHM.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.SORT) {
            ActivitySortTest.open(this@ActivityAlgorithmList)
        }).addItem(CommonListItemData.buildData(PageType.TRAVERSAL) {
            ActivityTraversalTest.open(this@ActivityAlgorithmList)
        }).addItem(CommonListItemData.buildData(PageType.LINK) {
            ActivityLinkTest.open(this@ActivityAlgorithmList)
        }).refreshList()
    }
}