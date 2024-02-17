package com.example.gitsample.algorithm.traversal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityTraversalTestBinding
import com.example.gitsample.widget.list.CommonListAdapter
import com.example.multi.cell.ClassCellManager

/**
 * Created by wyyu on 2024/1/29.
 **/
class ActivityTraversalTest : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityTraversalTest::class.java))
        }
    }

    private var listAdapter: CommonListAdapter<Class<out Any>, Any>? = null
    private val traversalList = ArrayList<TraversalItemData>()

    private lateinit var binding: ActivityTraversalTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraversalTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initView()
        initList()
        loadList()
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.TRAVERSAL.title)

        binding.btn.setOnClickListener {
            tryTraversal()
        }
    }

    private fun tryTraversal() {
        val rootNode = TraversalUtils.buildRootNode()
        traversalList.forEachIndexed { index, itemData ->
            itemData.resultList.clear()
            itemData.resultList.addAll(TraversalUtils.traversal(itemData.traversalType, rootNode))
            listAdapter?.updateItem(index, itemData)
        }
    }

    private fun initList() {
        listAdapter = CommonListAdapter(ClassCellManager(), binding.list)
        listAdapter!!.registerItem(TraversalItemData::class.java, TraversalItemHolder())

        binding.list.adapter = listAdapter
        binding.list.layoutManager = LinearLayoutManager(this@ActivityTraversalTest)
    }

    private fun loadList() {
        traversalList.addAll(TraversalItemData.buildTraversalList())
        listAdapter?.initList(traversalList)
    }
}