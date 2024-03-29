package com.example.gitsample.system.permission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityPermissionCheckBinding
import com.example.gitsample.utils.ZToast
import com.example.gitsample.widget.list.CommonListItemData

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

    private val typeDataMap = HashMap<PermissionItemType, CommonListItemData>()
    private val typeIntMap = HashMap<PermissionItemType, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    override fun onResume() {
        super.onResume()
        checkAndRefresh()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.PERMISSION.title)
    }

    private fun initList() {
        for (type in PermissionItemType.values()) {
            val itemData = CommonListItemData.buildData(type) {
                if (PermissionChecker.hasPermission(type)) {
                    ZToast.show("已获得该权限")
                } else {
                    requestPermission(type)
                }
            }
            typeDataMap[type] = itemData
            typeIntMap[type] = -1
            binding.list.addItem(itemData)
        }
        binding.list.refreshList()
    }

    private fun requestPermission(type: PermissionItemType) {
        PermissionChecker.requestPermission(type, object : PermissionRequestCallback {
            override fun onDenied() {
                ZToast.show("已拒绝该权限")
            }

            override fun onGranted() {
                checkAndRefresh(type)
            }
        })
    }

    private fun checkAndRefresh() {
        if (typeDataMap.keys.isEmpty()) {
            return
        }
        for (type in typeDataMap.keys) {
            checkAndRefresh(type)
        }
    }

    private fun checkAndRefresh(type: PermissionItemType) {
        val data = typeDataMap[type]
        val permissionValue = if (PermissionChecker.hasPermission(type)) {
            1
        } else {
            0
        }
        if (data == null || typeIntMap[type] == permissionValue) {
            return
        }
        typeIntMap[type] = permissionValue
        data.alert = resources.getString(
            if (permissionValue == 1) {
                R.string.permission_has_request
            } else {
                R.string.permission_to_request
            }
        )
        data.alertColor = if (permissionValue == 1) {
            R.color.ct_3
        } else {
            R.color.color_alert
        }
        binding.list.refreshItem(data)
    }
}