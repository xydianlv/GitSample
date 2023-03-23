package com.example.gitsample.system.permission

interface PermissionRequestCallback {

    fun onDenied()

    fun onGranted()
}