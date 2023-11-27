package com.example.gitsample.base.init

import com.example.gitsample.base.init.atomic.UIUtilsInitDelegate
import com.example.gitsample.base.init.check.MainCheckDelegate

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