package com.example.gitsample.main

import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityMainBinding
import com.example.gitsample.function.ActivityFunctionList
import com.example.gitsample.base.PageType

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
        binding.mainList.addItem(PageType.FUNCTION.title, PageType.FUNCTION.info) {
            ActivityFunctionList.open(this)
        }.addItem(PageType.CANVAS.title, PageType.CANVAS.info) {

        }.refreshList()
    }
}