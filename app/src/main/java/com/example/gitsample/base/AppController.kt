package com.example.gitsample.base

import android.app.Application
import android.content.Context
import com.example.base.manager.AnalyticManager
import com.example.init.AppInitDelegate
import com.example.init.utils.AppLaunchChecker

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