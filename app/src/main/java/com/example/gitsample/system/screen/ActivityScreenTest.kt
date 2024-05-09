package com.example.gitsample.system.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityScreenTestBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityScreenTest : BaseActivity<ActivityScreenTestBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityScreenTest::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityScreenTestBinding {
        return ActivityScreenTestBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SCREEN_TEST.title)
    }

    private fun initList() {
        binding.list.addItem(buildItemData(ScreenType.DARK_ACTION_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR)
        }).addItem(buildItemData(ScreenType.DARK_ACTION_BAR_HIDE_STATUS) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_STATUS)
        }).addItem(buildItemData(ScreenType.DARK_ACTION_BAR_TRANSPARENT_STATUS) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_TRANSPARENT_STATUS)
        }).addItem(buildItemData(ScreenType.DARK_ACTION_BAR_HIDE_NAVIGATION_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_NAVIGATION_BAR)
        }).addItem(buildItemData(ScreenType.DARK_ACTION_BAR_HIDE_SYSTEM_BAR) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_HIDE_SYSTEM_BAR)
        }).addItem(buildItemData(ScreenType.DARK_ACTION_BAR_FULLSCREEN) {
            ActivityDarkActionBar.open(this, ScreenType.DARK_ACTION_BAR_FULLSCREEN)
        }).addItem(buildItemData(ScreenType.NO_ACTION_BAR) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR)
        }).addItem(buildItemData(ScreenType.NO_ACTION_BAR_HIDE_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_HIDE_STATUS)
        }).addItem(buildItemData(ScreenType.NO_ACTION_BAR_TRANSPARENT_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_TRANSPARENT_STATUS)
        }).addItem(buildItemData(ScreenType.NO_ACTION_BAR_APPOINT_COLOR_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_BAR_APPOINT_COLOR_STATUS)
        }).addItem(buildItemData(ScreenType.NO_ACTION_FULLSCREEN) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_FULLSCREEN)
        }).addItem(buildItemData(ScreenType.NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS) {
            ActivityNoActionBar.open(this, ScreenType.NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS)
        }).refreshList()
    }

    private fun buildItemData(
        screenType: ScreenType,
        listener: View.OnClickListener
    ): CommonListItemData {
        return CommonListItemData.obj(screenType.title, screenType.info)
            .clickListener(listener)
            .arrow(true)
    }
}