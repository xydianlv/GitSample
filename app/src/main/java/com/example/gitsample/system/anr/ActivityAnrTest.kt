package com.example.gitsample.system.anr

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityAnrTestBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ActivityAnrTest : BaseActivity<ActivityAnrTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAnrTest::class.java))
        }
    }

    private var broadcast: AnrTestBroadcast? = null

    override fun getViewBinding(): ActivityAnrTestBinding {
        return ActivityAnrTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver()
        initToolbar()
        initList()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (broadcast != null) {
            unregisterReceiver(broadcast)
            broadcast = null
        }
    }

    private fun registerReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            broadcast = AnrTestBroadcast()
            registerReceiver(broadcast, IntentFilter(AnrTestBroadcast.ACTION), RECEIVER_EXPORTED)
        }
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.ANR_TEST.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.Companion.obj(
            "KeyDispatchTimeOut", "输入事件5s内无响应，触发ANR"
        ).clickListener { tryAnrInput() }).addItem(CommonListItemData.Companion.obj(
            "BroadcastTimeOut", "onReceiver() 在 10s 内无响应，触发ANR"
        ).clickListener { tryAnrBroadcast() }).addItem(CommonListItemData.Companion.obj(
            "ServiceTimeOut", "onCreate();onStart();onBind() 在 20s 内无响应，触发ANR"
        ).clickListener { tryAnrService() }).refreshList()
    }

    private fun tryAnrInput() {
        lifecycleScope.launch(Dispatchers.Main) {
            runBlocking {
                delay(10000L)
            }
        }
    }

    private fun tryAnrBroadcast() {
        sendBroadcast(Intent(AnrTestBroadcast.ACTION))
    }

    private fun tryAnrService() {
        startService(Intent(this, AnrTestService::class.java))
    }
}