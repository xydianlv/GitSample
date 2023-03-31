package com.example.gitsample.system.screen

import java.io.Serializable

enum class ScreenType(val title: String, val info: String) : Serializable {

    DARK_ACTION_BAR(
        "DarkActionBar",
        "带有 ActionBar 的界面样式"
    ),
    DARK_ACTION_BAR_HIDE_STATUS(
        "DarkActionBar HideStatus",
        "带有 ActionBar 的界面样式，隐藏顶部状态栏"
    ),
    DARK_ACTION_BAR_TRANSPARENT_STATUS(
        "DarkActionBar TransparentStatus",
        "带有 ActionBar 的界面样式，顶部状态栏背景色透明"
    ),
    DARK_ACTION_BAR_HIDE_NAVIGATION_BAR(
        "DarkActionBar HideNavigation",
        "带有 ActionBar 的界面样式，隐藏底部操作栏"
    ),
    DARK_ACTION_BAR_HIDE_SYSTEM_BAR(
        "DarkActionBar HideSystemBar",
        "带有 ActionBar 的界面样式，同时隐藏状态栏/操作栏"
    ),
    DARK_ACTION_BAR_FULLSCREEN(
        "DarkActionBar Fullscreen",
        "带有 ActionBar 的界面样式，设置全屏"
    ),
    NO_ACTION_BAR(
        "NoActionBar",
        "无 ActionBar 的界面样式"
    ),
    NO_ACTION_BAR_HIDE_STATUS(
        "NoActionBar HideStatus",
        "无 ActionBar 的界面样式，隐藏顶部状态栏"
    ),
    NO_ACTION_BAR_TRANSPARENT_STATUS(
        "NoActionBar TransparentStatus",
        "无 ActionBar 的界面样式，顶部状态栏背景色透明"
    ),
    NO_ACTION_BAR_APPOINT_COLOR_STATUS(
        "NoActionBar AppointColorStatus",
        "无 ActionBar 的界面样式，顶部状态栏背景色自定义"
    ),
    NO_ACTION_FULLSCREEN(
        "NoActionBar Fullscreen",
        "无 ActionBar 的界面样式，设置全屏"
    ),
    NO_ACTION_FULLSCREEN_TRANSPARENT_STATUS(
        "NoActionBar Fullscreen TransparentStatus",
        "无 ActionBar 的界面样式，设置全屏且状态栏背景透明"
    )
}