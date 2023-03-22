package com.example.gitsample.base

import android.app.Application
import com.example.gitsample.utils.UIUtils

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        UIUtils.init(baseContext)
        AnalyticManager.manager.initManager(this)
    }
}