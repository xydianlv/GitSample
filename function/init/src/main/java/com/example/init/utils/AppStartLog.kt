package com.example.init.utils

import com.example.init.AppInitModule
import com.example.utils.ZLog

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
            AppInitModule.UI -> {
                "UI"
            }
            AppInitModule.MAIN -> {
                "MainCheck"
            }
            AppInitModule.ANALYTIC -> {
                "Analytic"
            }
            else -> {
                "other"
            }
        }
    }
}