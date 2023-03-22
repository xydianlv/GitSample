package com.example.gitsample.utils

import android.widget.Toast
import com.example.gitsample.base.AnalyticManager

object ZToast {

    @JvmStatic
    fun show(toast: String) {
        val currentActivity = AnalyticManager.manager.currentActivity()
        if (currentActivity != null) {
            Toast.makeText(currentActivity, toast, Toast.LENGTH_SHORT).show()
        }
    }
}