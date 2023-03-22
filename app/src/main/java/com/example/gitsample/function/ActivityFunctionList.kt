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
        binding.list.addItem(PageType.GESTURE_LOCK.title, PageType.GESTURE_LOCK.info) {
            ActivityGestureLock.open(this)
        }.addItem(PageType.FINGER_LOCK.title, PageType.FINGER_LOCK.info) {
            ActivityFingerLock.open(this)
        }.addItem(PageType.SHOT_SCREEN.title, PageType.SHOT_SCREEN.info) {
            ActivityShotScreen.open(this)
        }.addItem(PageType.WALLPAPER.title, PageType.WALLPAPER.info) {
            ActivityFingerLock.open(this)
        }.addItem(PageType.FLOAT_DRAG.title, PageType.FLOAT_DRAG.info) {
            ActivityFingerLock.open(this)
        }.addItem(PageType.SHOT_CUT.title, PageType.SHOT_CUT.info) {
            ActivityFingerLock.open(this)
        }.refreshList()
    }
}