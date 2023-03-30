package com.example.gitsample.system.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

enum class PermissionItemType(val title: String, val info: String, val permission: String) {

    EXTERNAL_STORAGE(
        "ReadStorage",
        "本地磁盘读取权限(api28+ 移除 WriteStorage)",
        Manifest.permission.READ_EXTERNAL_STORAGE
    ),
    LOCATION_FINE(
        "LocationFine",
        "精准位置权限，一般为GPS",
        Manifest.permission.ACCESS_FINE_LOCATION
    ),
    LOCATION_COARSE(
        "LocationCoarse",
        "近似位置权限，一般为网络/WIFI",
        Manifest.permission.ACCESS_COARSE_LOCATION
    ),
    CAMERA(
        "Camera",
        "摄像头权限",
        Manifest.permission.CAMERA
    ),
    RECORD_AUDIO(
        "RecordAudio",
        "麦克风权限",
        Manifest.permission.RECORD_AUDIO
    ),
    WALLPAPER(
        "Wallpaper",
        "壁纸设置权限，注册后就会拥有该权限",
        Manifest.permission.SET_WALLPAPER
    ),
    CALENDAR(
        "Calendar",
        "日历读写权限",
        Manifest.permission.WRITE_CALENDAR
    ),
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    NOTIFICATION(
        "Notification",
        "推送权限，该权限仅在 api33 上生效",
        Manifest.permission.POST_NOTIFICATIONS
    )
}