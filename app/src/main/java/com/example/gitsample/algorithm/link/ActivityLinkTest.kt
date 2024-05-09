package com.example.gitsample.algorithm.link

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import com.example.base.PageType
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityLinkTestBinding
import com.example.widget.activity.BaseActivity

/**
 * Created by wyyu on 2024/2/20.
 **/
class ActivityLinkTest : BaseActivity<ActivityLinkTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityLinkTest::class.java))
        }
    }

    private val rootNode = LinkUtils.buildLink()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initClick()
    }

    override fun getViewBinding(): ActivityLinkTestBinding {
        return ActivityLinkTestBinding.inflate(layoutInflater)
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