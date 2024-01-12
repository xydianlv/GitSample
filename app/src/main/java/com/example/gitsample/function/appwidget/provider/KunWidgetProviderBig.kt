package com.example.gitsample.function.appwidget.provider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.example.gitsample.R
import com.example.gitsample.function.appwidget.KunWidgetManager
import com.example.gitsample.function.appwidget.KunWidgetUtils
import com.example.gitsample.function.appwidget.api.JsonKunWidget
import com.example.gitsample.function.appwidget.api.OnLoadJsonListener

class KunWidgetProviderBig : AppWidgetProvider() {

    companion object {

        private val ID_ARRAY_LAYOUT = arrayOf(
            R.id.bubble_2,
            R.id.bubble_0,
            R.id.bubble_3,
            R.id.bubble_1
        )
        private val ID_ARRAY_HOLDER = arrayOf(
            R.id.bubble_2_holder,
            R.id.bubble_0_holder,
            R.id.bubble_3_holder,
            R.id.bubble_1_holder
        )
        private val LAYOUT_ARRAY_BUBBLE = arrayOf(
            R.layout.layout_kun_bubble_15_1500,
            R.layout.layout_kun_bubble_25_2000,
            R.layout.layout_kun_bubble_15_1200,
            R.layout.layout_kun_bubble_25_1800
        )

        private const val LAYOUT_WIDGET = R.layout.layout_kun_widget_big
    }

    override fun onUpdate(context: Context?, manager: AppWidgetManager?, idArray: IntArray?) {
        KunWidgetUtils.onLog("KunBig.onUpdate")
        super.onUpdate(context, manager, idArray)
        tryLoadJsonData(context)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        KunWidgetUtils.onLog("KunBig.onDeleted")
        super.onDeleted(context, appWidgetIds)
        context?.let {
            if (!KunWidgetUtils.judgeHasKunWidget(it)) {
                KunWidgetManager.INSTANCE.cancelCountDown()
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        KunWidgetUtils.onLog("KunSmall.onReceive -> action : " + intent?.action)
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE == intent?.action) {
            val value = intent.getStringExtra(KunWidgetUtils.KEY_HANDLE_UPDATE)
            if (KunWidgetUtils.KEY_HANDLE_UPDATE_LOAD == value) {
                tryLoadJsonData(context)
            } else if (KunWidgetUtils.KEY_HANDLE_UPDATE_REFRESH_TIME == value) {
                tryRefreshTime(context)
            } else {
                super.onReceive(context, intent)
            }
        } else {
            super.onReceive(context, intent)
        }
    }

    private fun tryLoadJsonData(context: Context?) {
        KunWidgetUtils.onLog("KunBig.tryLoadJsonData")
        KunWidgetManager.INSTANCE.cancelCountDown()
        KunWidgetManager.INSTANCE.loadJsonValue(object : OnLoadJsonListener {
            override fun callback(json: JsonKunWidget?) {
                if (json == null) {
                    KunWidgetUtils.onLog("loadJsonData is null")
                } else if (context == null) {
                    KunWidgetUtils.onLog("context is null")
                } else {
                    KunWidgetViewsBuilder.tryShowWidget(buildKunWidgetParams(json, context))
                }
            }
        })
    }

    private fun buildKunWidgetParams(json: JsonKunWidget, context: Context): KunWidgetParams {
        KunWidgetUtils.onLog("KunBig.buildKunWidgetParams")
        return KunWidgetParams(
            context,
            json,
            ComponentName(context, KunWidgetProviderBig::class.java),
            LAYOUT_WIDGET,
            object : OnResCallback {
                override fun resCount(): Int {
                    return ID_ARRAY_LAYOUT.size
                }

                override fun idResLayout(index: Int): Int {
                    return ID_ARRAY_LAYOUT[index]
                }

                override fun idResHolder(index: Int): Int {
                    return ID_ARRAY_HOLDER[index]
                }

                override fun layoutResBubble(index: Int): Int {
                    return LAYOUT_ARRAY_BUBBLE[index]
                }
            })
    }

    private fun tryRefreshTime(context: Context?) {
        KunWidgetUtils.onLog("KunBig.tryRefreshTime")
        if (context == null) {
            KunWidgetUtils.onLog("context == null")
            return
        }
        val jsonKunWidget = KunWidgetManager.INSTANCE.jsonData()
        if (jsonKunWidget == null) {
            KunWidgetUtils.onLog("jsonKunWidget == null")
            return
        }
        KunWidgetViewsBuilder.tryRefreshCountDown(buildKunWidgetParams(jsonKunWidget, context))
    }
}