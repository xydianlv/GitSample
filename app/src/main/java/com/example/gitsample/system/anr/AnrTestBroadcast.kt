package com.example.gitsample.system.anr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class AnrTestBroadcast : BroadcastReceiver() {

    companion object {

        const val ACTION = "action_anr_test_broadcast"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            delay(20000L)
        }
    }
}