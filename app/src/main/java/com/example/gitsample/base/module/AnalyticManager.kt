package com.example.gitsample.base.module

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.lang.ref.WeakReference

class AnalyticManager private constructor() {

    companion object {
        val manager = ManagerHolder.manager
    }

    private object ManagerHolder {
        val manager = AnalyticManager()
    }

    private var weakReferenceActivity: WeakReference<Activity>? = null
    private var application: Application? = null
    private var processData: Int = -1

    fun initManager(app: Application) {
        application = app
        app.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                weakReferenceActivity = WeakReference(activity)
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }

    fun isMainProcess(): Boolean {
        if (processData == -1) {
            val packageName = appContext()?.packageName
            val processName = application
        }
        return processData == 1
    }

    fun currentActivity(): Activity? {
        return weakReferenceActivity?.get()
    }

    fun application(): Application? {
        return application
    }

    fun appContext(): Context? {
        return application?.baseContext
    }
}