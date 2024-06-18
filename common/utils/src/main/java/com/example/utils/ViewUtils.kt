package com.example.utils

import android.text.TextPaint
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import java.lang.Exception

object ViewUtils {

    /**
     * 设置文案展示
     *
     * @param view 文字控件
     * @param textValue 控件待展示文案
     * @param defaultValue 文案为空时，展示的默认文案，默认为 null
     * @param nullShow 文案为空时且默认文案为空时，是否隐藏控件，默认隐藏
     */
    fun setText(
        view: View?,
        textValue: String? = null,
        defaultValue: String? = null,
        nullShow: Boolean = false
    ) {
        if (view !is TextView) {
            return
        }
        val value = if (textValue.isNullOrEmpty()) {
            defaultValue
        } else {
            textValue
        }
        if (value.isNullOrEmpty()) {
            view.visibility = if (nullShow) {
                View.VISIBLE
            } else {
                View.GONE
            }
        } else {
            view.text = value
            view.visibility = View.VISIBLE
        }
    }

    /**
     * 设置中粗样式
     */
    fun setTextPaint(view: View?, isFakeBold: Boolean) {
        if (view !is TextView) {
            return
        }
        try {
            (view.paint as TextPaint).isFakeBoldText = isFakeBold
        } catch (e: Exception) {
            ZLog.e(e)
        }
    }
}