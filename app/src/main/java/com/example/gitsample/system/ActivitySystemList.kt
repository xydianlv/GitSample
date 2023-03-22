package com.example.gitsample.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivitySystemListBinding

class ActivitySystemList : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivitySystemList::class.java))
        }
    }

    private lateinit var binding: ActivitySystemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SYSTEM.title)
    }

    private fun initList() {

    }
}