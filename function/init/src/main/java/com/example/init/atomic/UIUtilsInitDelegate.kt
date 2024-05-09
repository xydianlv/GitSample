package com.example.init.atomic

import android.app.Application
import com.example.init.AppInitModule
import com.example.init.base.BaseInitDelegate
import com.example.utils.UIUtils

class UIUtilsInitDelegate : BaseInitDelegate() {

    override fun doInit(application: Application) {
        UIUtils.init(application.baseContext)
    }

    override fun onMainThread(): Boolean {
        return true
    }

    override fun onlyOnMainProcess(): Boolean {
        return true
    }

    override fun getModule(): Int {
        return AppInitModule.UI
    }
}