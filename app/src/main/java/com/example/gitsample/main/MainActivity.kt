package com.example.gitsample.main

import android.os.Bundle
import com.example.gitsample.algorithm.ActivityAlgorithmList
import com.example.gitsample.databinding.ActivityMainBinding
import com.example.gitsample.function.ActivityFunctionList
import com.example.base.PageType
import com.example.gitsample.canvas.ActivityCanvasList
import com.example.gitsample.system.ActivitySystemList
import com.example.gitsample.widget.ActivityWidgetList
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBar()
        initList()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun initBar() {
        binding.toolbar.initShow(PageType.MAIN.title)
    }

    private fun initList() {
        binding.mainList.addItem(CommonListItemData.obj(PageType.SYSTEM).clickListener {
            ActivitySystemList.open(this)
        }).addItem(CommonListItemData.obj(PageType.CANVAS).clickListener {
            ActivityCanvasList.open(this)
        }).addItem(CommonListItemData.obj(PageType.FUNCTION).clickListener {
            ActivityFunctionList.open(this)
        }).addItem(CommonListItemData.obj(PageType.WIDGET).clickListener {
            ActivityWidgetList.open(this)
        }).addItem(CommonListItemData.obj(PageType.ALGORITHM).clickListener {
            ActivityAlgorithmList.open(this)
        }).refreshList()
    }
}