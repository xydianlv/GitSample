package com.example.gitsample.system.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityScreenTestBinding
import com.example.gitsample.widget.list.CommonListItemData

class ActivityScreenTest : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityScreenTest::class.java))
        }
    }

    private lateinit var binding: ActivityScreenTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SCREEN_TEST.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR)
        }).addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR_HIDE_STATUS) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_STATUS)
        }).addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR_TRANSPARENT_STATUS) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_TRANSPARENT_STATUS)
        }).addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR_HIDE_NAVIGATION_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_NAVIGATION_BAR)
        }).addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR_HIDE_SYSTEM_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_SYSTEM_BAR)
        }).addItem(CommonListItemData.buildData(ScreenType.DARK_ACTION_BAR_FULLSCREEN) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_FULLSCREEN)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_BAR) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_BAR_HIDE_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_HIDE_STATUS)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_BAR_TRANSPARENT_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_TRANSPARENT_STATUS)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_BAR_APPOINT_COLOR_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_APPOINT_COLOR_STATUS)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_FULLSCREEN) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_FULLSCREEN)
        }).addItem(CommonListItemData.buildData(ScreenType.NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS)
        }).refreshList()
    }
}