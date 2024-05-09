package com.example.gitsample.system.info

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import com.example.utils.MemoryUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

object SystemInfoUtils {

    @JvmStatic
    fun loadSystemInfo(context: Context, callback: (list: ArrayList<SystemInfoData>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.IO) {
                val list: ArrayList<SystemInfoData> = ArrayList()
                loadDeviceInfo().forEach { (key, value) ->
                    list.add(SystemInfoData(key, value))
                }
                loadScreenInfo(context).forEach { (key, value) ->
                    list.add(SystemInfoData(key, value))
                }
                loadSDCardInfo().forEach { (key, value) ->
                    list.add(SystemInfoData(key, value))
                }
                loadRamInfo(context).forEach { (key, value) ->
                    list.add(SystemInfoData(key, value))
                }
                list
            }
            callback.invoke(list)
        }
    }

    @JvmStatic
    private fun loadDeviceInfo(): Map<String, String> {
        return LinkedHashMap<String, String>().apply {
            this["用户名称"] = Build.USER
            this["产品名称"] = Build.PRODUCT
            this["机型信息"] = Build.MODEL
            this["机型版本"] = Build.DISPLAY
            this["手机厂商"] = Build.MANUFACTURER
            this["品牌名称"] = Build.BRAND
            this["设备名称"] = Build.DEVICE
            this["芯片信息"] = Build.HARDWARE
            this["芯片型号"] = Build.BOARD
            this["主机信息"] = Build.HOST
            this["手机ID"] = Build.ID
            this["Android版本"] = Build.VERSION.RELEASE
            this["Api版本"] = Build.VERSION.SDK_INT.toString()
            this["国家"] = Locale.getDefault().country
            this["语言"] = Locale.getDefault().language
        }
    }

    @JvmStatic
    private fun loadScreenInfo(context: Context): Map<String, String> {
        return LinkedHashMap<String, String>().apply {
            this["屏幕宽度"] = context.resources.displayMetrics.widthPixels.toString() + "px"
            this["屏幕高度"] = context.resources.displayMetrics.heightPixels.toString() + "px"
        }
    }

    @JvmStatic
    private fun loadSDCardInfo(): Map<String, String> {
        return LinkedHashMap<String, String>().apply {
            val filePath = Environment.getDataDirectory().absolutePath
            val fileSize = MemoryUtils.getMemorySize(filePath, false)
            val freeFileSize = MemoryUtils.getMemorySize(filePath, true)

            this["存储空间大小"] = MemoryUtils.memorySizeFormat(fileSize)
            this["可用空间大小"] = MemoryUtils.memorySizeFormat(freeFileSize)
        }
    }

    @JvmStatic
    private fun loadRamInfo(context: Context): Map<String, String> {
        return LinkedHashMap<String, String>().apply {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE)
            if (am is ActivityManager) {
                val memoryInfo = ActivityManager.MemoryInfo()
                am.getMemoryInfo(memoryInfo)
                this["RAM 总大小"] = MemoryUtils.memorySizeFormat(memoryInfo.totalMem)
                this["RAM 可用大小"] = MemoryUtils.memorySizeFormat(memoryInfo.availMem)
            }
        }
    }
}
