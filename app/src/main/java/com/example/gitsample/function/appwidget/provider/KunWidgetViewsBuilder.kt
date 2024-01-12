package com.example.gitsample.function.appwidget.provider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.TypedValue
import android.view.View
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.example.gitsample.R
import com.example.gitsample.function.appwidget.KunWidgetManager
import com.example.gitsample.function.appwidget.KunWidgetUtils
import com.example.gitsample.function.appwidget.api.KunBubble
import com.example.gitsample.function.shortcut.ActivityShortcutTarget
import com.example.gitsample.utils.ParamsBuilder
import com.example.gitsample.utils.PendingIntentUtils
import com.example.gitsample.utils.PendingType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object KunWidgetViewsBuilder {

    @JvmStatic
    fun tryShowWidget(params: KunWidgetParams) {
        KunWidgetUtils.onLog("KunWidgetViewsBuilder.tryShowWidget")
        buildRemoteViews(params) {
            val appWidgetManager = AppWidgetManager.getInstance(params.context)
            if (appWidgetManager == null) {
                KunWidgetUtils.onLog("appWidgetManager == null")
                return@buildRemoteViews
            }
            val componentName = params.componentName
            val idArray = appWidgetManager.getAppWidgetIds(componentName)
            if (idArray == null || idArray.isEmpty()) {
                KunWidgetUtils.onLog("idArray == null || idArray.isEmpty()")
                return@buildRemoteViews
            }
            appWidgetManager.updateAppWidget(idArray, it)
            if (params.jsonKunWidget.hasCountDown()) {
                KunWidgetManager.INSTANCE.startCountDown()
            }
        }
    }

    @JvmStatic
    private fun buildRemoteViews(params: KunWidgetParams, callback: (views: RemoteViews) -> Unit) {
        KunWidgetUtils.onLog("KunWidgetViewsBuilder.buildRemoteViews")
        val views = RemoteViews(params.context.packageName, params.layout)
        views.setTextViewText(R.id.level, params.jsonKunWidget.levelInfo())
        views.setTextViewText(R.id.length, params.jsonKunWidget.lengthInfo())
        views.setOnClickPendingIntent(R.id.layout_root, buildLaunchIntent(params.context))

        val resCount = params.callback.resCount()
        for (index in 0 until resCount) {
            views.removeAllViews(params.callback.idResHolder(index))
            views.setViewVisibility(params.callback.idResLayout(index), View.INVISIBLE)
        }
        params.jsonKunWidget.bubbleList?.forEachIndexed { index, kunBubble ->
            if (index < resCount) {
                views.setViewVisibility(params.callback.idResLayout(index), View.VISIBLE)
                val bubbleRemoteViews = buildBubble(params, kunBubble, index)
                views.addView(params.callback.idResHolder(index), bubbleRemoteViews)
            }
        }
        buildKunBitmap(params.context, params.jsonKunWidget.kunImageUrl()) {
            if (it == null) {
                val resIdKunHolder = com.example.resource.R.mipmap.img_kun_holder
                views.setImageViewResource(R.id.img_kun, resIdKunHolder)
            } else {
                views.setImageViewBitmap(R.id.img_kun, it)
            }
            callback.invoke(views)
        }
    }

    @JvmStatic
    private fun buildLaunchIntent(context: Context): PendingIntent {
        KunWidgetUtils.onLog("KunWidgetViewsBuilder.buildBubbleShow")
        val intent = Intent(context, ActivityShortcutTarget::class.java)
        val params = ParamsBuilder(PendingType.ACTIVITY, intent)
            .context(context)
            .build()
        return PendingIntentUtils.buildPendingIntent(params)
    }

    @JvmStatic
    private fun buildBubble(params: KunWidgetParams, bubble: KunBubble, index: Int): RemoteViews {
        KunWidgetUtils.onLog("KunWidgetViewsBuilder.buildBubbleShow")
        val bubbleViews = if (bubble.showPrizeAlpha()) {
            RemoteViews(params.context.packageName, KunWidgetParams.LAYOUT_BUBBLE)
        } else {
            RemoteViews(params.context.packageName, params.callback.layoutResBubble(index))
        }
        bubbleViews.setImageViewResource(R.id.bubble_back, bubble.bubbleRes())
        bubbleViews.setTextViewTextSize(
            R.id.bubble_text,
            TypedValue.COMPLEX_UNIT_SP,
            bubble.textSize()
        )
        bubbleViews.setTextViewText(R.id.bubble_text, bubble.textInfo())
        if (bubble.showPrize()) {
            bubbleViews.setViewVisibility(R.id.bubble_img, View.VISIBLE)
        }
        if (bubble.showPrizeAlpha()) {
            bubbleViews.setViewVisibility(R.id.bubble_alpha, View.VISIBLE)
        }
        return bubbleViews
    }

    @JvmStatic
    private fun buildKunBitmap(context: Context, url: String?, callback: (img: Bitmap?) -> Unit) {
        KunWidgetUtils.onLog("KunWidgetViewsBuilder.buildKunBitmap")
        CoroutineScope(Dispatchers.Main).launch {
            if (url.isNullOrEmpty()) {
                callback.invoke(null)
            }
            val bitmap = withContext(Dispatchers.IO) {
                try {
                    Glide.with(context).asBitmap().load(url).submit().get()
                } catch (e: Exception) {
                    null
                }
            }
            callback.invoke(bitmap)
        }
    }

    @JvmStatic
    fun tryRefreshCountDown(params: KunWidgetParams) {
        val views = RemoteViews(params.context.packageName, params.layout)
        val resCount = params.callback.resCount()
        params.jsonKunWidget.bubbleList?.forEachIndexed { index, kunBubble ->
            if (index < resCount && kunBubble.showPrizeAlpha()) {
                val bubbleViews = buildBubble(params, kunBubble, index)
                views.removeAllViews(params.callback.idResHolder(index))
                views.addView(params.callback.idResHolder(index), bubbleViews)
            }
        }
        val appWidgetManager = AppWidgetManager.getInstance(params.context) ?: return
        val componentName = params.componentName
        val idArray = appWidgetManager.getAppWidgetIds(componentName) ?: return
        appWidgetManager.updateAppWidget(idArray, views)
        params.jsonKunWidget.onTimeUpdate()
    }
}