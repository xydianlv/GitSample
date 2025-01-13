package com.example.gitsample.system.jar.utils

import android.content.Context
import com.example.base.FileType
import com.example.gitsample.system.file.PathManager
import com.example.gitsample.system.jar.build.IJarLoader
import com.example.utils.ZLog
import com.example.widget.view.ZToast
import dalvik.system.DexClassLoader
import java.io.File
import java.lang.Exception

object JarLoadUtils {

    private const val FILE_NAME = "jarTest.jar"
    private const val FILE_NAME_DEX = "jarTest-dex.jar"

    fun loadJarFile(context: Context) {
        try {
            val jarFile = File(getOutJarPath())
            if (!jarFile.exists()) {
                ZToast.show("jarFile not exists")
                return
            }
            val classLoader =
                DexClassLoader(jarFile.absolutePath, getInnerJarPath(), null, context.classLoader)

            val classValue =
                classLoader.loadClass("com/example/gitsample/system/jar/build/JarLoaderImpl")
            val classObj = classValue.getDeclaredConstructor().newInstance()
            if (classObj is IJarLoader) {
                ZToast.show(classObj.toastValue())
            }
        } catch (e: Exception) {
            ZToast.show(e.toString())
        }
    }

    private fun getOutJarPath(): String {
        return PathManager.manager().extraCachePath(FileType.ROOT) + FILE_NAME_DEX
    }

    private fun getInnerJarPath(): String {
        return PathManager.manager().innerDataPath(FileType.TEMP) + FILE_NAME_DEX
    }
}