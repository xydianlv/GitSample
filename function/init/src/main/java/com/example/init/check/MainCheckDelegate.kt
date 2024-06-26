package com.example.init.check

import android.app.Application
import com.example.init.AppInitModule
import com.example.init.base.BaseInitDelegate

class MainCheckDelegate : BaseInitDelegate() {

    override fun doInit(application: Application) {

    }

    override fun onMainThread(): Boolean {
        return true
    }

    override fun onlyOnMainProcess(): Boolean {
        return true
    }

    override fun getLinkModuleArray(): IntArray {
        return intArrayOf(AppInitModule.UI)
    }

    override fun getModule(): Int {
        return AppInitModule.MAIN
    }
}