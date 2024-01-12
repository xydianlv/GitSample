package com.example.gitsample.utils

import android.os.Build

object SDKVersionUtils {

    @JvmStatic
    fun sdkVersionAbove29(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.Q
    }

    @JvmStatic
    fun sdkVersionAbove30(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.R
    }
}