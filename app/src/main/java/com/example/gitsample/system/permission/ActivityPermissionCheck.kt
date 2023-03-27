package com.example.gitsample.system.permission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityPermissionCheckBinding

/**
 * 权限需要先声明，才能再动态申请
 */

class ActivityPermissionCheck : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityPermissionCheck::class.java))
        }
    }

    private lateinit var binding: ActivityPermissionCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    override fun onResume() {
        super.onResume()
        binding.storage.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.EXTERNAL_STORAGE))
        binding.locationFine.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.LOCATION_FINE))
        binding.locationCoarse.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.LOCATION_COARSE))
        binding.camera.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.CAMERA))
        binding.record.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.RECORD_AUDIO))
        binding.wallpaper.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.WALLPAPER))
        binding.calendar.refreshStatus(PermissionChecker.hasPermission(PermissionItemType.CALENDAR))
    }

    private fun initActivity() {
        initToolbar()
        initItem()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.PERMISSION.title)
    }

    private fun initItem() {
        binding.storage.initItemShow(PermissionItemType.EXTERNAL_STORAGE)
        binding.locationFine.initItemShow(PermissionItemType.LOCATION_FINE)
        binding.locationCoarse.initItemShow(PermissionItemType.LOCATION_COARSE)
        binding.camera.initItemShow(PermissionItemType.CAMERA)
        binding.record.initItemShow(PermissionItemType.RECORD_AUDIO)
        binding.wallpaper.initItemShow(PermissionItemType.WALLPAPER)
        binding.calendar.initItemShow(PermissionItemType.CALENDAR)
    }
}