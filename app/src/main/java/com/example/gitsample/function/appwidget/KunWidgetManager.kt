package com.example.gitsample.function.appwidget

import android.os.Handler
import android.os.Looper
import com.example.gitsample.base.module.AnalyticManager
import com.example.gitsample.function.appwidget.api.JsonKunWidget
import com.example.gitsample.function.appwidget.api.KunWidgetApi
import com.example.gitsample.function.appwidget.api.OnLoadJsonListener

enum class KunWidgetManager {
    INSTANCE;

    companion object {

        private const val DIVIDE_TIME_LOAD_JSON = 60 * 1000L

        private const val TIME_DIVIDE_FIRST = 4000L
        private const val TIME_DIVIDE = 980L
        private const val MSG_WHAT = 1
    }

    private val listenerList: ArrayList<OnLoadJsonListener> = ArrayList()
    private var jsonData: JsonKunWidget? = null
    private var lastTime: Long = 0L
    private var onLoad: Boolean = false

    private val api = KunWidgetApi()

    private var onCountDown: Boolean = false
    private var handler: Handler? = null

    init {
        handler = Handler(Looper.getMainLooper()) { msg ->
            KunWidgetUtils.onLog("KunWidgetManager.onMessage")
            if (msg.what == MSG_WHAT) {
                KunWidgetUtils.tryRefreshKunPrizeTime(AnalyticManager.manager.appContext())
                if (onCountDown) {
                    KunWidgetUtils.onLog("KunWidgetManager.sendMsg From Handler")
                    handler?.sendEmptyMessageDelayed(MSG_WHAT, TIME_DIVIDE)
                }
                true
            } else {
                false
            }
        }
    }

    // 获取桌面组件数据
    fun loadJsonValue(listener: OnLoadJsonListener) {
        val curTime = System.currentTimeMillis()
        if (curTime - lastTime > DIVIDE_TIME_LOAD_JSON) {
            tryLoadJsonData()
        }
        if (onLoad) {
            listenerList.add(listener)
        } else {
            listener.callback(jsonData)
        }
    }

    // 调用获取数据接口
    private fun tryLoadJsonData() {
        if (onLoad) {
            return
        }
        api.getKunWidgetJson {
            jsonData = it
            onLoad = false
            lastTime = System.currentTimeMillis()

            listenerList.forEach { listener ->
                listener.callback(jsonData)
            }
            listenerList.clear()
        }
        onLoad = true
    }

    // 启动倒计时
    fun startCountDown() {
        KunWidgetUtils.onLog("KunWidgetManager.startCountDown")
        if (onCountDown || handler?.hasMessages(MSG_WHAT) == true) {
            KunWidgetUtils.onLog("onCountDown || handler?.hasMessages(MSG_WHAT) == true")
            return
        }
        handler?.sendEmptyMessageDelayed(MSG_WHAT, TIME_DIVIDE_FIRST)
        onCountDown = true
    }

    // 关闭倒计时
    fun cancelCountDown() {
        KunWidgetUtils.onLog("KunWidgetManager.cancelCountDown")
        onCountDown = false
        handler?.removeMessages(MSG_WHAT)
    }

    // 获取当前 KunWidget 数据
    fun jsonData(): JsonKunWidget? {
        return jsonData
    }
}