package com.example.gitsample.base.init.base

import android.app.Application
import android.os.Looper
import androidx.annotation.WorkerThread
import com.example.gitsample.base.module.AnalyticManager
import com.example.gitsample.base.init.AppInitEngine
import com.example.gitsample.base.init.utils.AppStartLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseInitDelegate : IInitDelegate {

    private val linkModuleMap: HashMap<Int, Boolean> = HashMap()
    private var onInitFinish: Boolean = false
    private var onInit: Boolean = false
    private var startTime: Long = 0L

    override fun doInit() {
        initValue()
        initModule()
    }

    override fun onMainThread(): Boolean {
        return false
    }

    override fun onInitFinish(): Boolean {
        return onInitFinish
    }

    override fun needCrashInit(): Boolean {
        return false
    }

    override fun needPrepareLooper(): Boolean {
        return false
    }

    override fun onlyOnMainProcess(): Boolean {
        return true
    }

    override fun onLinkModuleInit(module: Int) {
        if (linkModuleMap.size != getLinkModuleArray().size) {
            initValue()
        }
        linkModuleMap[module] = true
        initModule()
    }

    override fun getLinkModuleArray(): IntArray {
        return intArrayOf()
    }

    @WorkerThread
    abstract fun doInit(application: Application)

    private fun initValue() {
        getLinkModuleArray().forEach { module ->
            val linkDelegate = AppInitEngine.INSTANCE.getDelegate(module)
            if (linkDelegate != null) {
                linkModuleMap[module] = linkDelegate.onInitFinish() == true
            }
        }
    }

    private fun initModule() {
        if (onInitFinish) {
            AppInitEngine.INSTANCE.onInitFinish(getModule())
            return
        }
        if (!checkInit()) {
            return
        }
        if (onlyOnMainProcess() && !AnalyticManager.manager.isMainProcess()) {
            return
        }
        if (onInit) {
            return
        }
        this.onInit = true
        this.startTime = System.currentTimeMillis()
        if (onMainThread()) {
            try {
                AppStartLog.onLog(0, AppStartLog.name(getModule()))
                val application = AnalyticManager.manager.application()
                if (application == null) {
                    onInitError(Exception("Init Failure : application is null"))
                } else {
                    doInit(application)
                    onInitSuccess()
                }
            } catch (e: Exception) {
                if (needCrashInit()) {
                    throw Exception("Init Failure : $e")
                } else {
                    onInitError(e)
                }
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                AppStartLog.onLog(0, AppStartLog.name(getModule()))
                val application = AnalyticManager.manager.application()
                val result = if (application == null) {
                    Exception("Init Failure : application is null")
                } else {
                    withContext(Dispatchers.IO) {
                        if (needPrepareLooper()) {
                            Looper.prepare()
                        }
                        try {
                            doInit(application)
                            null
                        } catch (e: Exception) {
                            e
                        }
                    }
                }
                if (result == null) {
                    onInitSuccess()
                } else if (needCrashInit()) {
                    throw Exception("Init Failure : $result")
                } else {
                    onInitError(result)
                }
            }
        }
    }

    private fun onInitSuccess() {
        onInitFinish = true
        onInit = false

        val divideTime = System.currentTimeMillis() - startTime
        val logInfo = AppStartLog.name(getModule()) + " init finish -> divideTime : " + divideTime
        AppStartLog.onLog(0, logInfo)

        AppInitEngine.INSTANCE.onInitFinish(getModule())
    }

    private fun onInitError(e: Exception) {
        onInit = false

        val divideTime = System.currentTimeMillis() - startTime
        val logInfo = AppStartLog.name(getModule()) + " init error -> divideTime : " + divideTime
        AppStartLog.onLog(0, "$logInfo errorInfo : $e")
    }

    private fun checkInit(): Boolean {
        linkModuleMap.forEach { entity ->
            if (!entity.value) {
                return false
            }
        }
        return true
    }
}