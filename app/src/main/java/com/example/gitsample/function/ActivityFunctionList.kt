package com.example.gitsample.function

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityFunctionListBinding
import com.example.gitsample.function.applayout.ActivityAppLayoutTest
import com.example.gitsample.function.appwidget.ActivityAppWidgetTest
import com.example.gitsample.function.drag.ActivityFloatDrag
import com.example.gitsample.function.lock.gesture.ActivityGestureLock
import com.example.gitsample.function.screen.ActivityShotScreen
import com.example.gitsample.function.shortcut.ActivityShortcut
import com.example.gitsample.function.wallpaper.ActivityWallpaper
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityFunctionList : BaseActivity<ActivityFunctionListBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFunctionList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityFunctionListBinding {
        return ActivityFunctionListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FUNCTION.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.GESTURE_LOCK).clickListener {
            ActivityGestureLock.open(this)
        }).addItem(CommonListItemData.obj(PageType.SHOT_SCREEN).clickListener {
            ActivityShotScreen.open(this)
        }).addItem(CommonListItemData.obj(PageType.WALLPAPER).clickListener {
            ActivityWallpaper.open(this)
        }).addItem(CommonListItemData.obj(PageType.FLOAT_DRAG).clickListener {
            ActivityFloatDrag.open(this)
        }).addItem(CommonListItemData.obj(PageType.SHORTCUT).clickListener {
            ActivityShortcut.open(this)
        }).addItem(CommonListItemData.obj(PageType.APP_WIDGET).clickListener {
            ActivityAppWidgetTest.open(this)
        }).addItem(CommonListItemData.obj(PageType.APP_LAYOUT).clickListener {
            ActivityAppLayoutTest.open(this)
        }).refreshList()
    }
}