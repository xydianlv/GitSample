package com.example.gitsample.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityWidgetListBinding
import com.example.gitsample.widget.dialog.ActivityMyDialogList
import com.example.gitsample.widget.fragment.ActivityFragmentTest
import com.example.gitsample.widget.shadow.ActivityShadowLayoutTest
import com.example.gitsample.widget.tab.ActivityTabLayoutTest
import com.example.gitsample.widget.text.ActivityTextViewTest
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityWidgetList : BaseActivity<ActivityWidgetListBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityWidgetList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityWidgetListBinding {
        return ActivityWidgetListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.WIDGET.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.DIALOG).clickListener {
            ActivityMyDialogList.open(this)
        }).addItem(CommonListItemData.obj(PageType.TEXT_VIEW).clickListener {
            ActivityTextViewTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.FRAGMENT).clickListener {
            ActivityFragmentTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.TAB_LAYOUT).clickListener {
            ActivityTabLayoutTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.SHADOW_LAYOUT).clickListener {
            ActivityShadowLayoutTest.open(this)
        }).refreshList()
    }
}