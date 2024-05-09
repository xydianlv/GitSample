package com.example.gitsample.system.file

import android.os.Environment
import com.example.base.FileType
import com.example.base.manager.AnalyticManager
import com.example.gitsample.base.AppConstants
import java.io.File

class PathManager private constructor() : IPath {

    companion object {

        @JvmStatic
        fun manager(): PathManager {
            return ManagerHolder.manager
        }
    }

    private object ManagerHolder {
        val manager = PathManager()
    }

    // SD卡分区数据缓存地址
    private var extraCachePath: String? = null

    // SD卡分区文件存放地址
    private var extraFilePath: String? = null

    // SD卡分区存放视频、图片文件地址
    private var extraMediaPath: String? = null

    // 内部分区缓存地址
    private var cachePath: String? = null

    // 内部分区文件存放地址
    private var filePath: String? = null

    init {
        initInnerPath()
        initExtraFilePath()
        initExtraCachePath()
        initExtraMediaPath()
    }

    private fun initInnerPath() {
        val context = AnalyticManager.manager.appContext()
        cachePath = context?.cacheDir?.absolutePath
        filePath = context?.filesDir?.absolutePath
    }

    private fun initExtraFilePath() {
        val context = AnalyticManager.manager.appContext()
        val filePath = context?.getExternalFilesDir("")?.absolutePath
        extraFilePath = if (filePath?.isNotEmpty() == true) {
            checkPath(filePath)
        } else {
            val packageName = if (context == null) {
                AppConstants.PACKAGE_NAME
            } else {
                context.packageName
            }
            val destPath = "/Android/data/$packageName/files/"
            val fileDir = File(Environment.getExternalStorageDirectory(), destPath)
            checkPath(fileDir.absolutePath)
        }
        if (extraFilePath.isNullOrEmpty() || !File(extraFilePath!!).canWrite()) {
            extraFilePath = filePath
        }
    }

    private fun initExtraCachePath() {
        val context = AnalyticManager.manager.appContext()
        val cachePath = context?.externalCacheDir?.absolutePath
        extraCachePath = if (cachePath?.isNotEmpty() == true) {
            checkPath(cachePath)
        } else {
            val packageName = if (context == null) {
                AppConstants.PACKAGE_NAME
            } else {
                context.packageName
            }
            val destPath = "/Android/data/$packageName/cache/"
            val cacheDir = File(Environment.getExternalStorageDirectory(), destPath)
            checkPath(cacheDir.absolutePath)
        }
        if (extraCachePath.isNullOrEmpty() || !File(extraCachePath!!).canWrite()) {
            extraCachePath = cachePath
        }
    }

    private fun initExtraMediaPath() {
        val context = AnalyticManager.manager.appContext()
        val mediaPath = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)?.absolutePath
        extraMediaPath = if (mediaPath?.isNotEmpty() == true) {
            checkPath(mediaPath + "/" + FileType.DCIM_DIR + "/")
        } else {
            if (extraFilePath.isNullOrEmpty()) {
                initExtraFilePath()
            }
            checkPath(extraFilePath + "/" + FileType.MEDIA + "/")
        }
    }

    private fun checkPath(filePath: String): String {
        val file = File(filePath)
        if (!file.isDirectory) {
            file.delete()
        }
        if (!file.exists()) {
            file.mkdirs()
        }
        return filePath
    }

    override fun innerDataPath(@FileType fileType: String): String {
        if (filePath.isNullOrEmpty()) {
            initInnerPath()
        }
        return if (FileType.ROOT == fileType) {
            checkPath("$filePath/data/")
        } else {
            checkPath("$filePath/data/$fileType/")
        }
    }

    override fun innerCachePath(@FileType fileType: String): String {
        if (cachePath.isNullOrEmpty()) {
            initInnerPath()
        }
        return if (FileType.ROOT == fileType) {
            checkPath("$cachePath/")
        } else {
            checkPath("$cachePath/$fileType/")
        }
    }

    override fun extraFilePath(@FileType fileType: String): String {
        if (extraFilePath.isNullOrEmpty()) {
            initExtraFilePath()
        }
        return if (FileType.ROOT == fileType) {
            checkPath("$extraFilePath/")
        } else {
            checkPath("$extraFilePath/$fileType/")
        }
    }

    override fun extraCachePath(@FileType fileType: String): String {
        if (extraCachePath.isNullOrEmpty()) {
            initExtraCachePath()
        }
        return if (FileType.ROOT == fileType) {
            checkPath("$extraCachePath/")
        } else {
            checkPath("$extraCachePath/$fileType/")
        }
    }

    override fun saveMediaPath(): String {
        if (extraMediaPath.isNullOrEmpty()) {
            initExtraMediaPath()
        }
        return extraMediaPath!!
    }
}