package com.example.gitsample.system.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.resource.R
import com.example.utils.ZLog

object NotifyTestUtils {

    private const val CHANNEL_ID = "git_sample_notify_channel"

    fun tryInitNotifyChannel(context: Context) {
        try {
            getNotifyManager(context)?.createNotificationChannel(getNotifyChannel())
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }

    private fun getNotifyManager(context: Context): NotificationManager? {
        return try {
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        } catch (e: Exception) {
            ZLog.e(e)
            null
        }
    }

    private fun getNotifyChannel(): NotificationChannel {
        return NotificationChannel(CHANNEL_ID, "消息通知", NotificationManager.IMPORTANCE_HIGH)
    }

    fun trySendNotify(context: Context, type: NotifyTestType) {
        try {
            val notifyBuilder = getNotifyBuilder(context)
            dispatchNotifyBuilder(notifyBuilder, type)
            getNotifyManager(context)?.notify(1, notifyBuilder.build())
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }

    private fun getNotifyBuilder(context: Context): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setTicker(context.resources.getString(R.string.app_name))
            .setContentTitle(context.resources.getString(R.string.text_short_cn))
            .setContentText(context.resources.getString(R.string.text_long_cn)).setAutoCancel(true)
    }

    private fun dispatchNotifyBuilder(builder: NotificationCompat.Builder, type: NotifyTestType) {
        when (type) {
            NotifyTestType.NOTIFY_BUILDER -> {
                builder.setDefaults(0)
            }

            NotifyTestType.NOTIFY_BUILDER_V -> {
                builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            }

            NotifyTestType.NOTIFY_BUILDER_S -> {
                builder.setDefaults(NotificationCompat.DEFAULT_SOUND)
            }

            NotifyTestType.NOTIFY_BUILDER_VS -> {
                builder.setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
            }

            NotifyTestType.NOTIFY_CHANNEL_V -> {
                val notifyChannel = getNotifyChannel()
                notifyChannel.enableVibration(true)
                notifyChannel.vibrationPattern = longArrayOf(0, 200, 200, 200)
            }

            NotifyTestType.NOTIFY_CHANNEL_S -> {
                val notifyChannel = getNotifyChannel()
                notifyChannel.enableVibration(false)
                notifyChannel.setSound(
                    Settings.System.DEFAULT_NOTIFICATION_URI,
                    AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
                )

            }

            NotifyTestType.NOTIFY_CHANNEL_VS -> {
                val notifyChannel = getNotifyChannel()
                notifyChannel.enableVibration(true)
                notifyChannel.vibrationPattern = longArrayOf(0, 200, 200, 200)
                notifyChannel.setSound(
                    Settings.System.DEFAULT_NOTIFICATION_URI,
                    AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
                )
            }
        }
    }
}