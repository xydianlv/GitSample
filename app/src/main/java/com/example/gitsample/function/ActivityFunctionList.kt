package com.example.gitsample.function

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityFunctionListBinding
import com.example.gitsample.function.lock.finger.ActivityFingerLock
import com.example.gitsample.function.lock.gesture.ActivityGestureLock
import com.example.gitsample.function.screen.ActivityShotScreen
import com.example.gitsample.function.wallpaper.ActivityWallpaper
import com.example.gitsample.widget.list.CommonListItemData

class ActivityFunctionList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFunctionList::class.java))
        }
    }

    private lateinit var binding: ActivityFunctionListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFunctionListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FUNCTION.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.GESTURE_LOCK) {
            ActivityGestureLock.open(this)
        }).addItem(CommonListItemData.buildData(PageType.SHOT_SCREEN) {
            ActivityShotScreen.open(this)
        }).addItem(CommonListItemData.buildData(PageType.WALLPAPER) {
            ActivityWallpaper.open(this)
        }).addItem(CommonListItemData.buildData(PageType.FLOAT_DRAG) {
            ActivityFingerLock.open(this)
        }).addItem(CommonListItemData.buildData(PageType.SHOT_CUT) {
            ActivityFingerLock.open(this)
        }).refreshList()
    }
}