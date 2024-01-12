package com.example.gitsample.utils

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
}