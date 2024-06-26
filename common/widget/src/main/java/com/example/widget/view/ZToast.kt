package com.example.widget.view

import android.widget.Toast
import com.example.base.manager.AnalyticManager
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