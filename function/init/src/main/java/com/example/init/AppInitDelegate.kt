package com.example.init

import com.example.init.atomic.UIUtilsInitDelegate
import com.example.init.check.MainCheckDelegate
import com.example.init.utils.AppStartLog

object AppInitDelegate {

    @JvmStatic
    fun init() {
        AppStartLog.onLog(0, "AppInitDelegate.init()")
        AppInitEngine.INSTANCE.group(
            UIUtilsInitDelegate(),
            MainCheckDelegate()
        ).init()
    }
}