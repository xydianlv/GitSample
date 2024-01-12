package com.example.gitsample.utils

import android.os.StatFs

object MemoryUtils {

    @JvmStatic
    fun getMemorySize(filePath: String, freeSize: Boolean): Long {
        return try {
            val fileStat = StatFs(filePath)
            val blockSize = fileStat.blockSizeLong
            val blockCount = fileStat.blockCountLong
            val freeBlockCount = fileStat.availableBlocksLong
            if (freeSize) {
                blockSize * freeBlockCount
            } else {
                blockSize * blockCount
            }
        } catch (e: Exception) {
            ZLog.e(e)
            0L
        }
    }

    @JvmStatic
    fun memorySizeFormat(memorySize: Long): String {
        val divide = 1024.0f
        val kbPre = memorySize / divide
        if (kbPre <= 0.0f) {
            return memorySize.toString() + "Bytes"
        }
        val mbPre = kbPre / divide
        if (mbPre <= 0.0f) {
            return String.format("%.2f", kbPre) + "KB"
        }
        val gbPre = mbPre / divide
        return if (gbPre <= 0.0f) {
            String.format("%.2f", mbPre) + "MB"
        } else {
            String.format("%.2f", gbPre) + "GB"
        }
    }
}