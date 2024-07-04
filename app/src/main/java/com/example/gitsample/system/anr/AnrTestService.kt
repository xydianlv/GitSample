package com.example.gitsample.system.anr

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.widget.view.ZToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class AnrTestService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        ZToast.show("AnrTestService.onBind()")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        ZToast.show("AnrTestService.onCreate()")
        runBlocking {
            delay(22000L)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ZToast.show("AnrTestService.onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }
}