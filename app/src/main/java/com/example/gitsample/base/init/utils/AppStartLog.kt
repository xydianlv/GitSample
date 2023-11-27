package com.example.gitsample.base.init.utils

import com.example.gitsample.base.init.AppInitModule
import com.example.gitsample.utils.ZLog

object AppStartLog {

    private const val TAG = "AppStartLogTag"

    @JvmStatic
    fun onLog(type: Int, logInfo: String) {
        val preTag = getTagFromType(type)
        ZLog.d(TAG, "InitTag : $preTag  logInfo : $logInfo")
    }

    @JvmStatic
    fun getTagFromType(type: Int): String {
        return when (type) {
            0 -> {
                "Application"
            }
            1 -> {
                "Splash"
            }
            2 -> {
                "Main"
            }
            else -> {
                "Init"
            }
        }
    }

    @JvmStatic
    fun name(@AppInitModule module: Int): String {
        return when (module) {
            AppInitModule.ALL -> {
                "all"
            }
            else -> {
                "other"
            }
        }
    }
}