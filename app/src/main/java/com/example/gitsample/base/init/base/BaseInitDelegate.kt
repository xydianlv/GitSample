package com.example.gitsample.base.init.base

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.annotation.WorkerThread
import com.example.gitsample.base.AnalyticManager
import com.example.gitsample.base.init.AppInitEngine
import com.example.gitsample.base.init.AppLaunchThreadPool
import com.example.gitsample.base.init.AppStartLog
import com.example.gitsample.utils.ZLog

abstract class BaseInitDelegate : IInitDelegate {

    companion object {
        private const val MSG_INIT_ERROR: Int = -1
    }

    private val handler: Handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            if (msg.what == getModule()) {
                onInitFinish = true
                onInit = false

                val divideTime = System.currentTimeMillis() - startTime
                val logInfo =
                    AppStartLog.name(getModule()) + " init finish -> divideTime : " + divideTime
                AppStartLog.onLog(0, logInfo)

                AppInitEngine.INSTANCE.onInitFinish(getModule())
                return true
            }
            if (msg.what == MSG_INIT_ERROR) {
                onInit = false

                val divideTime = System.currentTimeMillis() - startTime
                val logInfo =
                    AppStartLog.name(getModule()) + " init error -> divideTime : " + divideTime
                AppStartLog.onLog(0, logInfo)
            }
            return false
        }
    })

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
        linkModuleMap[module] = true
        initModule()
    }

    override fun getLinkModuleArray(): IntArray {
        return intArrayOf()
    }

    @WorkerThread
    abstract fun doInit(application: Application)

    private fun baseInit(application: Application?) {
        if (application == null) {
            throw Exception("application is null")
        } else {
            doInit(application)
        }
    }

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
                baseInit(AnalyticManager.manager.application())
                handler.sendEmptyMessage(getModule())
            } catch (e: Exception) {
                if (needCrashInit()) {
                    throw Exception("Init Failure : $e")
                } else {
                    handler.sendEmptyMessage(MSG_INIT_ERROR)
                    ZLog.e(e)
                }
            }
        } else {
            AppLaunchThreadPool.get().execute {
                try {
                    if (needPrepareLooper()) {
                        Looper.prepare()
                    }
                    AppStartLog.onLog(0, AppStartLog.name(getModule()))
                    baseInit(AnalyticManager.manager.application())
                    handler.sendEmptyMessage(getModule())
                } catch (e: Exception) {
                    if (needCrashInit()) {
                        throw Exception("Init Failure : $e")
                    } else {
                        handler.sendEmptyMessage(MSG_INIT_ERROR)
                        ZLog.e(e)
                    }
                }
            }
        }
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