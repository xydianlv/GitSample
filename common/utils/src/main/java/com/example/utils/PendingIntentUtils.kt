package com.example.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.IntDef
import com.example.base.manager.AnalyticManager

object PendingIntentUtils {

    @JvmStatic
    fun buildPendingIntent(params: PendingIntentParams): PendingIntent {
        val value = if (SDKVersionUtils.sdkVersionAbove30()) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        return when (params.type) {
            PendingType.BROADCAST -> {
                PendingIntent.getBroadcast(params.context, params.requestCode, params.intent, value)
            }

            else -> {
                PendingIntent.getActivity(params.context, params.requestCode, params.intent, value)
            }
        }
    }
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    PendingType.ACTIVITY,
    PendingType.BROADCAST
)
annotation class PendingType {
    companion object {

        const val ACTIVITY = 0

        const val BROADCAST = 1
    }
}

class PendingIntentParams(
    @PendingType val type: Int,
    val intent: Intent,
    var context: Context?,
    var requestCode: Int
)

class ParamsBuilder(@PendingType type: Int, intent: Intent) {

    private val params = PendingIntentParams(
        type,
        intent,
        AnalyticManager.manager.appContext(),
        System.currentTimeMillis().toInt()
    )

    fun context(context: Context): ParamsBuilder {
        params.context = context
        return this
    }

    fun requestCode(requestCode: Int): ParamsBuilder {
        params.requestCode = requestCode
        return this
    }

    fun build(): PendingIntentParams {
        return params
    }
}