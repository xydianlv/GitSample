package com.example.gitsample.widget.dialog

/**
 * Created by wyyu on 2024/1/24.
 **/

enum class DialogType(val title: String, val info: String) {

    ALERT_DIALOG_SYSTEM("AlertDialog", "系统样式的提示型Dialog"),
    ALERT_DIALOG_MY("MAlertDialog", "自定义的提示型Dialog"),
    FULL_SCREEN_DIALOG("FullScreenDialog", "全屏的Dialog"),
    INPUT_DIALOG("InputDialog", "带输入框的Dialog")
}