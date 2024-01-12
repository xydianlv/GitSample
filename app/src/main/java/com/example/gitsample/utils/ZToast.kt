package com.example.gitsample.utils

import android.widget.Toast
import com.example.gitsample.base.module.AnalyticManager
import java.lang.ref.WeakReference

object ZToast {

    private var weakToast: WeakReference<Toast>? = null

    @JvmStatic
    fun show(toastValue: String) {
        val currentActivity = AnalyticManager.manager.currentActivity() ?: return
        weakToast?.get()?.cancel()
        weakToast = WeakReference(Toast.makeText(currentActivity, toastValue, Toast.LENGTH_SHORT))
        weakToast?.get()?.show()
    }
}