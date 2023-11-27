package com.example.gitsample.base

import android.app.Application
import com.example.gitsample.base.init.AppInitDelegate

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        AnalyticManager.manager.initManager(this)
        AppInitDelegate.init()
    }
}