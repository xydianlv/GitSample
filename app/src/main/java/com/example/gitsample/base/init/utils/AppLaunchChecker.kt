package com.example.gitsample.base.init.utils

import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.gitsample.base.module.AnalyticManager
import com.example.gitsample.base.init.AppInitEngine
import com.example.gitsample.base.init.AppInitModule
import com.example.gitsample.base.init.AppModuleInitCallback

object AppLaunchChecker {


    private const val LAUNCH_PATH_SPLASH = "com.example.gitsample.main.ActivitySplash"

    private const val CLASS_CLIENT_TRANSACTION =
        "android.app.servertransaction.ClientTransaction"
    private const val CLASS_ACTIVITY_THREAD = "android.app.ActivityThread"

    private const val METHOD_CURRENT_ACTIVITY_THREAD = "currentActivityThread"

    private const val FIELD_M_ACTIVITY_CALLBACKS = "mActivityCallbacks"
    private const val FIELD_M_CALLBACK = "mCallback"
    private const val FIELD_M_HANDLER = "mH"

    private const val KEY_LAUNCH_ACTIVITY_ITEM = "LaunchActivityItem"

    private const val TAG = "LaunchFromCheckerLog"

    private var onWaitMsgQueue: ArrayList<Message> = ArrayList()
    private var onWait: Boolean = false

    @JvmStatic
    fun check() {
        if (AnalyticManager.manager.isMainProcess()) {
            checkOnMainProcess()
        }
    }

    @JvmStatic
    private fun checkOnMainProcess() {
        try {
            Log.d(TAG, "AppLaunchChecker start check")
            // 反射拿到 ActivityThread 类型
            val activityThreadClass = Class.forName(CLASS_ACTIVITY_THREAD)
            // 通过 ActivityThread 中的 currentActivityThread() 方法拿到 ActivityThread 对象
            val activityThread = activityThreadClass.getMethod(METHOD_CURRENT_ACTIVITY_THREAD)
                .invoke(null)
            // 对象 activityThread 为 null，退出 Checker
            if (activityThread == null) {
                Log.e(TAG, "activityThread is null")
                return
            } else {
                Log.d(TAG, "activityThread : $activityThread")
            }
            // 反射拿到 ActivityThread 中的 mh = H extends Handler 对象Field
            val mHandlerField = activityThreadClass.getDeclaredField(FIELD_M_HANDLER)
            mHandlerField.isAccessible = true
            // 反射拿到 mH 对象
            val mHandler = mHandlerField.get(activityThread)
            // 对象 mHandler 为 null，退出 Checker
            if (mHandler == null || mHandler !is Handler) {
                Log.e(TAG, "mHandler is null or not Handler")
                return
            } else {
                Log.d(TAG, "mHandler : $mHandler")
            }

            // 反射拿到 Handler 中的 mCallback 对象Field
            val callbackField = Handler::class.java.getDeclaredField(FIELD_M_CALLBACK)
            callbackField.isAccessible = true
            // 构建可拦截的 Callback 对象
            callbackField.set(mHandler, object : Handler.Callback {
                override fun handleMessage(msg: Message): Boolean {
                    Log.d(TAG, "onReceive message : $msg")
                    if (checkHandleMsg(msg)) {
                        mHandler.handleMessage(msg)
                    } else {
                        waitInitAndHandleMsg(mHandler)
                    }
                    return true
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "AppLaunchChecker check error : $e")
        }
    }

    @JvmStatic
    private fun checkHandleMsg(msg: Message): Boolean {
        Log.d(TAG, "AppLaunchChecker start checkHandleMsg")
        val msgObjInfo = msg.obj?.toString()
        if (msgObjInfo.isNullOrEmpty() || !msgObjInfo.contains(CLASS_CLIENT_TRANSACTION)) {
            Log.d(TAG, "msgObj is empty or not ClientTransaction")
            return true
        }
        try {
            val clientTransactionClass = msg.obj.javaClass
            val fieldCallbackList =
                clientTransactionClass.getDeclaredField(FIELD_M_ACTIVITY_CALLBACKS)
            fieldCallbackList.isAccessible = true

            val callbackListValue = fieldCallbackList.get(msg.obj)?.toString()
            if (callbackListValue.isNullOrEmpty()) {
                Log.d(TAG, "callbackListValue isNullOrEmpty")
                return true
            }
            Log.d(TAG, "callbackListValue : $callbackListValue")

            return if (callbackListValue.contains(KEY_LAUNCH_ACTIVITY_ITEM)) {
                Log.d(TAG, "callbackListValue contains LaunchActivityItem")
                if (launchFromNormalPath(callbackListValue)) {
                    Log.d(TAG, "launchActivityItem from normal launch")
                    true
                } else {
                    Log.d(TAG, "launchActivityItem from other launch")
                    onWaitMsgQueue.add(Message.obtain(msg))
                    false
                }
            } else {
                Log.d(TAG, "callbackListValue is not LaunchActivityItem")
                if (onWait) {
                    Log.d(TAG, "handler on wait")
                    onWaitMsgQueue.add(Message.obtain(msg))
                    false
                } else {
                    Log.d(TAG, "normal handle msg")
                    true
                }
            }
        } catch (e: Exception) {
            return true
        }
    }

    @JvmStatic
    private fun launchFromNormalPath(value: String): Boolean {
        return value.contains(LAUNCH_PATH_SPLASH)
    }

    @JvmStatic
    private fun waitInitAndHandleMsg(handler: Handler) {
        Log.d(TAG, "AppLaunchChecker waitInitAndHandleMsg")
        if (onWait) {
            Log.d(TAG, "has onWait queue suze : " + onWaitMsgQueue.size)
            return
        }
        Log.d(TAG, "start wait main init")
        AppInitEngine.INSTANCE.checkInit(AppInitModule.MAIN, object : AppModuleInitCallback {
            override fun onFinish() {
                Log.d(TAG, "on main init finish")
                if (onWaitMsgQueue.isEmpty()) {
                    Log.d(TAG, "msg queue is empty")
                    return
                }
                for (msg in onWaitMsgQueue) {
                    Log.d(TAG, "handle msg before init : $msg")
                    handler.handleMessage(msg)
                }
                onWaitMsgQueue.clear()
                onWait = false
            }
        })
        onWait = true
    }
}