package com.example.gitsample.algorithm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.algorithm.link.ActivityLinkTest
import com.example.gitsample.algorithm.sort.ActivitySortTest
import com.example.gitsample.algorithm.traversal.ActivityTraversalTest
import com.example.gitsample.databinding.ActivityAlgorithmListBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

/**
 * Created by wyyu on 2024/1/28.
 **/
class ActivityAlgorithmList : BaseActivity<ActivityAlgorithmListBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAlgorithmList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityAlgorithmListBinding {
        return ActivityAlgorithmListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.ALGORITHM.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.SORT).clickListener {
            ActivitySortTest.open(this@ActivityAlgorithmList)
        }).addItem(CommonListItemData.obj(PageType.TRAVERSAL).clickListener {
            ActivityTraversalTest.open(this@ActivityAlgorithmList)
        }).addItem(CommonListItemData.obj(PageType.LINK).clickListener {
            ActivityLinkTest.open(this@ActivityAlgorithmList)
        }).refreshList()
    }
}