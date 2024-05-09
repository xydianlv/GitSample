package com.example.utils

import android.util.Log

object ZLog {

    private const val DEFAULT_TAG = "GitSample"

    @JvmStatic
    fun d(log: String) {
        d(DEFAULT_TAG, log)
    }

    @JvmStatic
    fun d(tag: String, log: String) {
        Log.d(tag, log)
    }

    @JvmStatic
    fun i(log: String) {
        i(DEFAULT_TAG, log)
    }

    @JvmStatic
    fun i(tag: String, log: String) {
        Log.i(tag, log)
    }

    @JvmStatic
    fun e(log: String) {
        e(DEFAULT_TAG, log)
    }

    @JvmStatic
    fun e(exp: Throwable) {
        e(DEFAULT_TAG, exp.toString())
    }

    @JvmStatic
    fun e(tag: String, log: String) {
        Log.e(tag, log)
    }
}