package com.example.gitsample.main

import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityMainBinding
import com.example.gitsample.function.ActivityFunctionList
import com.example.gitsample.base.PageType
import com.example.gitsample.system.ActivitySystemList

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initBar()
        initList()
    }

    private fun initBar() {
        binding.toolbar.initShow(PageType.MAIN.title)
    }

    private fun initList() {
        binding.mainList.addItem(PageType.SYSTEM.title, PageType.SYSTEM.info) {
            ActivitySystemList.open(this)
        }.addItem(PageType.CANVAS.title, PageType.CANVAS.info) {

        }.addItem(PageType.FUNCTION.title, PageType.FUNCTION.info) {
            ActivityFunctionList.open(this)
        }.refreshList()
    }
}