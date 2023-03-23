package com.example.gitsample.base

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
    private var appContext: Context? = null

    fun initManager(app: Application) {
        appContext = app.baseContext
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
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

    fun currentActivity(): Activity? {
        return weakReferenceActivity?.get()
    }

    fun appContext(): Context? {
        return appContext
    }
}