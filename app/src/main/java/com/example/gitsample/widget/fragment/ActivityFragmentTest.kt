package com.example.gitsample.widget.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityFragmentTestBinding
import com.example.gitsample.widget.fragment.visible.ActivityFragmentVisibleTest
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityFragmentTest : BaseActivity<ActivityFragmentTestBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFragmentTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initList()
    }

    override fun getViewBinding(): ActivityFragmentTestBinding {
        return ActivityFragmentTestBinding.inflate(layoutInflater)
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.FRAGMENT.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.FRAGMENT_VISIBLE_TEST).clickListener {
            ActivityFragmentVisibleTest.open(this@ActivityFragmentTest)
        }).refreshList()
    }
}