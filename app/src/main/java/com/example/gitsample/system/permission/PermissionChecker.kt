package com.example.gitsample.system.permission

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.gitsample.base.module.AnalyticManager

object PermissionChecker {

    @JvmStatic
    fun hasPermission(permissionItem: PermissionItemType): Boolean {
        return hasPermission(permissionItem.permission)
    }

    @JvmStatic
    fun hasPermission(permissionValue: String): Boolean {
        val activity = AnalyticManager.manager.currentActivity()
        if (activity != null) {
            val result = ActivityCompat.checkSelfPermission(activity, permissionValue)
            return result == PackageManager.PERMISSION_GRANTED
        }
        val context = AnalyticManager.manager.appContext()
        if (context != null) {
            val result = ActivityCompat.checkSelfPermission(context, permissionValue)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    @JvmStatic
    fun requestPermission(permissionItem: PermissionItemType, callback: PermissionRequestCallback) {
        requestPermission(permissionItem.permission, callback)
    }

    @JvmStatic
    fun requestPermission(permissionValue: String, callback: PermissionRequestCallback) {
        if (hasPermission(permissionValue)) {
            callback.onGranted()
            return
        }
        ActivityPermissionRequest.setRequestCallback(callback)
        val activity = AnalyticManager.manager.currentActivity()
        if (activity != null) {
            ActivityPermissionRequest.open(activity, permissionValue)
            return
        }
        val context = AnalyticManager.manager.appContext()
        if (context != null) {
            ActivityPermissionRequest.open(context, permissionValue)
            return
        }
        callback.onDenied()
    }
}