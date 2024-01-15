package com.example.gitsample.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {

    @JvmStatic // 获取倒计时，countDownTime : 单位s
    fun getCountDown(countDownTime: Int): String {
        val hour = countDownTime / 3600
        val last = countDownTime % 3600
        val minute = last / 60
        val second = last % 60

        val hourInfo = if (hour >= 10) {
            hour.toString()
        } else {
            "0$hour"
        }
        val minuteInfo = if (minute >= 10) {
            minute.toString()
        } else {
            "0$minute"
        }
        val secondInfo = if (second >= 10) {
            second.toString()
        } else {
            "0$second"
        }
        return "$hourInfo:$minuteInfo:$secondInfo"
    }

    @JvmStatic
    fun getCurrentFormatTime(): String {
        val format = getFormatTime(System.currentTimeMillis())
        return format.substring(format.indexOf(" "), format.length)
    }

    @JvmStatic // 获取时间戳的格式表达，time 单位ms
    fun getFormatTime(time: Long): String {
        val format = "yyyy-MM-dd HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(Date(time))
    }
}