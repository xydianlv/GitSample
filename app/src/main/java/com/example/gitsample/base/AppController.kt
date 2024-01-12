package com.example.gitsample.base

import android.app.Application
import android.content.Context
import com.example.gitsample.base.init.AppInitDelegate
import com.example.gitsample.base.init.utils.AppLaunchChecker
import com.example.gitsample.base.module.AnalyticManager

class AppController : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        AnalyticManager.manager.initManager(this)
        init()
    }

    private fun init() {
        AppLaunchChecker.check()
        AppInitDelegate.init()
    }
}