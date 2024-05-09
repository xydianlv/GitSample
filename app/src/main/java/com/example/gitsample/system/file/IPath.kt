package com.example.gitsample.system.file

import com.example.base.FileType

interface IPath {

    // 私有存储空间中，存放数据的路径
    // 一般存放Json数据<浏览历史>、数据库数据<通知、私信>、相关交互的配置数据<气泡、Lottie、弹窗>
    fun innerDataPath(@FileType fileType: String): String

    // 私有存储空间中，存放缓存的路径
    fun innerCachePath(@FileType fileType: String): String

    // SD卡空间中存放文件的路径
    // 一般存放图片、视频、日志、音频、APK文件
    fun extraFilePath(@FileType fileType: String): String

    // SD卡空间缓存文件的路径
    // 一般缓存图片、视频、音频、头像等
    fun extraCachePath(@FileType fileType: String): String

    // 系统目录中存放文件的目录
    // 与 extraFilePath 的区别是，前者是在应用目录中创建一个存放文件的地址，后者是在 DCIM 中创建一个存放的地址
    fun saveMediaPath(): String
}