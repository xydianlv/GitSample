package com.example.gitsample.algorithm.link

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityLinkTestBinding

/**
 * Created by wyyu on 2024/2/20.
 **/
class ActivityLinkTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityLinkTest::class.java))
        }
    }

    private lateinit var binding: ActivityLinkTestBinding
    private val rootNode = LinkUtils.buildLink()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initView()
        initClick()
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.LINK.title)
        binding.link.text = LinkUtils.buildResultStr(rootNode)
    }

    private fun initClick() {
        val clickListener = OnClickListener {
            when (it.id) {
                R.id.re_change -> {
                    tryReChange()
                }
            }
        }
        binding.reChange.setOnClickListener(clickListener)
    }

    private fun tryReChange() {
        val resultNode = LinkUtils.changeList(rootNode)
        binding.resultReChange.text = LinkUtils.buildResultStr(resultNode)
    }
}