package com.example.gitsample.function.appwidget.api

import com.example.gitsample.utils.TimeUtils
import com.example.resource.R
import com.google.gson.annotations.SerializedName

data class JsonKunWidget(
    @SerializedName(value = "kun_level")
    var kunLevel: Int = 0,
    @SerializedName(value = "kun_cm")
    var kunCm: Int = 0,
    @SerializedName(value = "kun_url")
    var kunUrl: String? = null,
    @SerializedName(value = "list")
    var bubbleList: ArrayList<KunBubble>? = null
) {

    fun levelInfo(): String {
        return "Lv$kunLevel"
    }

    fun lengthInfo(): String {
        return kunCm.toString() + "厘米"
    }

    fun onTimeUpdate() {
        bubbleList?.forEach {
            it.onTimeUpdate()
        }
    }

    fun kunImageUrl(): String? {
        return kunUrl
    }

    fun hasCountDown(): Boolean {
        if (bubbleList.isNullOrEmpty()) {
            return false
        }
        for (bubble in bubbleList!!) {
            if (bubble.showPrizeAlpha()) {
                return true
            }
        }
        return false
    }

    fun updateValue(kunWidget: JsonKunWidget) {
        kunLevel = kunWidget.kunLevel
        kunCm = kunWidget.kunCm
        kunUrl = kunWidget.kunUrl

        bubbleList = ArrayList()
        kunWidget.bubbleList?.forEach {
            bubbleList!!.add(it)
        }
    }
}

data class KunBubble(
    @SerializedName(value = "type") // 1-刮刮乐气泡；2-任务气泡；3-倒计时气泡
    var type: Int = 0,
    @SerializedName(value = "task_point") // 可获取的厘米数
    var taskPoint: Int = 0,
    @SerializedName(value = "count_down") // 气泡倒计时，单位 秒
    var countDown: Int = 0,
) {

    fun textInfo(): String {
        return when (type) {
            2 -> {
                "$taskPoint\ncm"
            }
            3 -> {
                if (countDown > 0) {
                    TimeUtils.getCountDown(countDown)
                } else {
                    ""
                }
            }
            else -> {
                ""
            }
        }
    }

    fun textSize(): Float {
        return when (type) {
            2 -> {
                18.0f
            }
            3 -> {
                if (countDown > 0) {
                    12.0f
                } else {
                    0.0f
                }
            }
            else -> {
                0.0f
            }
        }
    }

    fun bubbleRes(): Int {
        return if (type == 2) {
            R.mipmap.img_kun_bubble_cm
        } else {
            R.mipmap.img_kun_bubble_prize
        }
    }

    fun showPrize(): Boolean {
        return type == 1 || (type == 3 && countDown <= 0)
    }

    fun showPrizeAlpha(): Boolean {
        return type == 3 && countDown > 0
    }

    fun onTimeUpdate() {
        if (type == 3 && countDown > 0) {
            countDown--
        }
    }
}