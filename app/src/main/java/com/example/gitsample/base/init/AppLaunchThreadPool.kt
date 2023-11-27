package com.example.gitsample.base.init

import com.example.gitsample.utils.ZLog
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object AppLaunchThreadPool {

    private val executor = Executors.newCachedThreadPool { r -> Thread(r) }

    @JvmStatic
    fun get(): ExecutorService {
        return executor
    }

    @JvmStatic
    fun shutDown() {
        try {
            executor.shutdown()
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }
}