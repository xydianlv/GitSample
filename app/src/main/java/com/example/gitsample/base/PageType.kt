package com.example.gitsample.base

enum class PageType(val title: String, val info: String) {

    MAIN("MainTest", "主界面"),

    // MAIN 下面的跳转界面
    FUNCTION("FunctionList", "一些好玩的小功能"),
    CANVAS("CanvasList", "Canvas绘制方法收集"),
    SYSTEM("SystemList", "系统属性和接口实现"),

    // FUNCTION 下面的跳转界面
    GESTURE_LOCK("GestureLock", "九宫格解锁"),
    FINGER_LOCK("FingerLock", "指纹解锁"),
    SHOT_SCREEN("ShotScreen", "截屏"),
    WALLPAPER("Wallpaper", "设置锁屏/壁纸"),
    FLOAT_DRAG("FloatDrag", "悬浮图标自动靠边"),
    SHOT_CUT("ShotCut", "桌面快捷方式"),

    // SYSTEM 下面的跳转界面
    PERMISSION("PermissionCheck", "权限申请"),
    FILE_MANAGER("FileManager", "文件管理"),
    SCREEN_TEST("ScreenTest", "屏幕展示样式，状态栏、全屏等"),
}