package com.example.gitsample.function.appwidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.gitsample.base.module.PreferencesManager
import com.example.gitsample.function.appwidget.provider.KunWidgetProviderBig
import com.example.gitsample.function.appwidget.provider.KunWidgetProviderSmall
import com.example.gitsample.function.appwidget.provider.KunWidgetType
import com.example.gitsample.function.shortcut.ShortCutPermissionChecker
import com.example.gitsample.function.shortcut.ShortcutConstants
import com.example.gitsample.utils.ZLog
import com.example.gitsample.utils.ZToast

object KunWidgetUtils {

    private const val KEY_LAST_REFRESH_TIME = "key_last_refresh_time"
    private const val REFRESH_DIVIDE_TIME = 15 * 60 * 1000L

    private const val LOG_TAG = "KunWidgetLogTag"

    const val KEY_HANDLE_UPDATE_REFRESH_TIME = "key_handle_update_refresh_time"
    const val KEY_HANDLE_UPDATE_LOAD = "key_handle_update_load"
    const val KEY_HANDLE_UPDATE = "key_handle_update"

    private var logListener: OnWidgetLogListener? = null

    @JvmStatic
    fun onLog(logInfo: String) {
        ZLog.d(LOG_TAG, logInfo)
        logListener?.onLog(logInfo)
    }

    @JvmStatic
    fun registerLogListener(logListener: OnWidgetLogListener?) {
        KunWidgetUtils.logListener = logListener
    }

    @JvmStatic
    fun addKunWidget(context: Context, @KunWidgetType type: Int, call: (success: Boolean) -> Unit) {
        Log.d(LOG_TAG, "KunWidgetHelper.addBigKunWidget")
        checkPermission(context) {
            if (it) {
                when (type) {
                    KunWidgetType.KUN_WIDGET_SMALL -> {
                        val widgetName = ComponentName(context, KunWidgetProviderSmall::class.java)
                        call.invoke(addKunWidget(context, widgetName))
                    }
                    KunWidgetType.KUN_WIDGET_BIG -> {
                        val widgetName = ComponentName(context, KunWidgetProviderBig::class.java)
                        call.invoke(addKunWidget(context, widgetName))
                    }
                }
            } else {
                call.invoke(false)
            }
        }
    }

    @JvmStatic
    private fun checkPermission(context: Context, callback: (success: Boolean) -> Unit) {
        Log.d(LOG_TAG, "KunWidgetHelper.checkPermission")
        val permission = ShortCutPermissionChecker.checkPermission(context)
        if (ShortcutConstants.PERMISSION_ASK == permission
            || ShortcutConstants.PERMISSION_GRANTED == permission
            || ShortcutConstants.PERMISSION_UNKNOWN == permission
        ) {
            Log.d(LOG_TAG, "on permission granted")
            callback.invoke(true)
        }
//        else if (ROM.isMIUI() || ROM.isEMUI() || ROM.isHuawei()) {
//            Log.d(LOG_TAG, "on permission denied")
//            ZToast.show("请前往应用设置界面，打开桌面快捷方式权限")
//            callback.invoke(false)
//        }
        else {
            ZToast.show("添加失败，请从桌面添加小组件")
            callback.invoke(false)
        }
    }

    @JvmStatic
    private fun addKunWidget(context: Context, providerName: ComponentName): Boolean {
        return try {
            Log.d(LOG_TAG, "KunWidgetHelper.addKunWidget")
            val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)
            val requestResult = appWidgetManager.requestPinAppWidget(providerName, null, null)
            if (requestResult) {
                ZToast.show("添加成功")
                true
            } else {
                ZToast.show("添加失败，请从桌面添加小组件")
                false
            }
        } catch (e: Exception) {
            Log.d(LOG_TAG, "KunWidgetHelper.addBigKunWidget -> error : $e")
            ZToast.show("添加失败，请从桌面添加小组件")
            false
        }
    }

    @JvmStatic
    fun tryRefreshKunWidget(context: Context?, ignoreTime: Boolean) {
        Log.d(LOG_TAG, "KunWidgetHelper.tryRefreshKunWidget")
        if (context == null || !judgeHasKunWidget(context)) {
            Log.d(LOG_TAG, "context == null || !judgeHasKunWidget(context)")
            return
        }
        val sharedPreferences = PreferencesManager.INSTANCE.commonSharedPreferences()
        val lastTime = sharedPreferences.getLong(KEY_LAST_REFRESH_TIME, 0L)
        val curTime = System.currentTimeMillis()
        if (curTime - lastTime < REFRESH_DIVIDE_TIME && !ignoreTime) {
            Log.d(LOG_TAG, "on time limit")
            return
        }
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        intent.putExtra(KEY_HANDLE_UPDATE, KEY_HANDLE_UPDATE_LOAD)
        Log.d(LOG_TAG, "start sendBroadcast")
        context.sendBroadcast(intent)
        sharedPreferences.edit().putLong(KEY_LAST_REFRESH_TIME, curTime).apply()
    }

    @JvmStatic
    fun tryRefreshKunPrizeTime(context: Context?) {
        onLog("KunWidgetUtils.tryRefreshKunPrizeTime")
        if (context == null) {
            onLog("context == null")
            return
        }
        if (!judgeHasKunWidget(context)) {
            onLog("!judgeHasKunWidget(context)")
            return
        }
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        intent.putExtra(KEY_HANDLE_UPDATE, KEY_HANDLE_UPDATE_REFRESH_TIME)
        Log.d(LOG_TAG, "start sendBroadcast")
        context.sendBroadcast(intent)
    }

    @JvmStatic
    fun judgeHasKunWidget(context: Context): Boolean {
        Log.d(LOG_TAG, "KunWidgetUtils.judgeHasKunWidget")
        val hasSmallWidget = hasKunWidget(context, KunWidgetType.KUN_WIDGET_SMALL)
        val hasBigWidget = hasKunWidget(context, KunWidgetType.KUN_WIDGET_BIG)
        return hasSmallWidget || hasBigWidget
    }

    @JvmStatic
    private fun hasKunWidget(context: Context, @KunWidgetType widgetType: Int): Boolean {
        Log.d(LOG_TAG, "KunWidgetUtils.hasKunWidget")
        return try {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            if (appWidgetManager == null) {
                Log.d(LOG_TAG, "appWidgetManager == null")
                false
            } else if (widgetType == KunWidgetType.KUN_WIDGET_BIG) {
                Log.d(LOG_TAG, "widgetType == KUN_WIDGET_BIG")
                val componentName = ComponentName(context, KunWidgetProviderBig::class.java)
                val idArray = appWidgetManager.getAppWidgetIds(componentName)
                idArray != null && idArray.isNotEmpty()
            } else if (widgetType == KunWidgetType.KUN_WIDGET_SMALL) {
                Log.d(LOG_TAG, "widgetType == KUN_WIDGET_SMALL")
                val componentName = ComponentName(context, KunWidgetProviderSmall::class.java)
                val idArray = appWidgetManager.getAppWidgetIds(componentName)
                idArray != null && idArray.isNotEmpty()
            } else {
                Log.d(LOG_TAG, "widgetType unValid")
                false
            }
        } catch (e: Exception) {
            Log.d(LOG_TAG, "judgeHasKunWidget error : $e")
            false
        }
    }
}