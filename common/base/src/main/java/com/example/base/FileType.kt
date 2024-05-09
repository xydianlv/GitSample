package com.example.base

import androidx.annotation.StringDef

@StringDef(
    FileType.ROOT,
    FileType.MEDIA,
    FileType.LOG,
    FileType.TEMP,
    FileType.DCIM_DIR
)
@Retention(AnnotationRetention.SOURCE)
annotation class FileType {
    companion object {
        const val ROOT = "" // 根目录
        const val MEDIA = "media" // 视频/图片文件存放目录
        const val LOG = "log" // 日志存放地址
        const val TEMP = "temp" // SD卡的缓存文件存放地址
        const val DCIM_DIR = "git_sample" // DCIM 目录下的媒体文件目录
    }
}