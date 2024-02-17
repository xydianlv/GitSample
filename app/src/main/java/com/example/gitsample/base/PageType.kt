package com.example.gitsample.base

enum class PageType(val title: String, val info: String) {

    MAIN("MainTest", "主界面"),

    // MAIN 下面的跳转界面
    ALGORITHM("AlgorithmList", "一些基本的算法实现"),
    WIDGET("WidgetList", "一些可复用的自定义控件"),
    FUNCTION("FunctionList", "一些好玩的小功能"),
    CANVAS("CanvasList", "Canvas绘制方法收集"),
    SYSTEM("SystemList", "系统提供的一些属性和接口实现"),

    // SYSTEM 下面的跳转界面
    PERMISSION("PermissionCheck", "权限申请"),
    FILE_MANAGER("FileManager", "文件管理"),
    SCREEN_TEST("ScreenTest", "屏幕展示样式，状态栏、全屏等"),
    SYSTEM_INFO("SystemInfo", "手机相关的所有信息"),
    KOTLIN_TEST("KotlinTest", "Kotlin 协程相关总结"),

    // CANVAS 下面的跳转界面
    CANVAS_FIGURE("CanvasFigure", "Canvas 绘制几何形状"),
    CANVAS_TEXT("CanvasText", "Canvas 绘制文字"),
    CANVAS_PATH("CanvasPath", "Canvas 绘制路径"),
    CANVAS_IMAGE("CanvasImage", "Canvas 绘制图片"),
    CANVAS_X_FER_MODE("CanvasXFerMode", "Canvas 绘制时添加特效"),

    // FUNCTION 下面的跳转界面
    GESTURE_LOCK("GestureLock", "九宫格解锁"),
    SHOT_SCREEN("ShotScreen", "截屏"),
    WALLPAPER("Wallpaper", "设置锁屏/壁纸"),
    FLOAT_DRAG("FloatDrag", "悬浮图标自动靠边"),
    SHORTCUT("Shortcut", "桌面快捷方式"),
    SHORTCUT_TARGET("ShortcutTarget", "桌面快捷方式跳转界面"),
    APP_WIDGET("AppWidgetTest", "桌面组件"),

    // WIDGET 下面的跳转界面
    DIALOG("Dialog", "各种样式的Dialog"),

    // ALGORITHM 下面的跳转界面
    SORT("SortTest", "常见的排序算法"),
    TRAVERSAL("TraversalTest", "二叉树的遍历"),
}