package com.example.gitsample.system.jar.utils

import android.content.Context
import com.example.base.FileType
import com.example.gitsample.system.file.PathManager
import com.example.gitsample.system.jar.test.IJarLoader
import com.example.widget.view.ZToast
import dalvik.system.DexClassLoader
import java.io.File
import java.lang.Exception

object JarKtLoadUtils {

    fun loadJarFile(context: Context, jarType: JarType) {
        try {
            val jarFile = File(getOutJarPath(jarType))
            if (!jarFile.exists()) {
                ZToast.show("jarFile not exists")
                return
            }
            val extraPath = jarFile.absolutePath
            val innerPath = getInnerJarPath(jarType)
            val classLoader = DexClassLoader(extraPath, innerPath, null, context.classLoader)

            val classValue = classLoader.loadClass(jarType.classPath)
            val classObj = classValue.getDeclaredConstructor().newInstance()
            if (classObj is IJarLoader) {
                ZToast.show(classObj.toastValue())
            }
        } catch (e: Exception) {
            ZToast.show(e.toString())
        }
    }

    private fun getOutJarPath(jarType: JarType): String {
        return PathManager.manager().extraCachePath(FileType.ROOT) + jarType.fileName
    }

    private fun getInnerJarPath(jarType: JarType): String {
        return PathManager.manager().innerDataPath(FileType.TEMP) + jarType.fileName
    }
}