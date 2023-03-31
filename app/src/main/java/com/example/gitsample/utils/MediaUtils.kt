package com.example.gitsample.utils

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import com.example.gitsample.base.AnalyticManager
import com.example.gitsample.system.file.FileType
import java.io.File
import java.io.OutputStream
import java.nio.file.Files

object MediaUtils {

    @JvmStatic
    fun saveMediaToAlbum(filePath: String?) {
        if (TextUtils.isEmpty(filePath)) {
            return
        }
        val fileName = filePath!!.substring(filePath.lastIndexOf("/"))
        saveMediaToAlbum(filePath, fileName)
    }

    @JvmStatic
    fun saveMediaToAlbum(filePath: String?, fileName: String?) {
        val mediaFile = if (TextUtils.isEmpty(filePath)) {
            null
        } else {
            File(filePath!!)
        }
        if (mediaFile?.exists() == false) {
            return
        }
        when (filePath!!.substring(filePath.lastIndexOf("."))) {
            ".jpeg", ".jpg" -> {
                saveImageToAlbum(filePath, getFileName(fileName, ".jpeg"))
            }
            ".gif" -> {
                saveGifToAlbum(filePath, getFileName(fileName, ".gif"))
            }
            ".mp4" -> {
                saveVideoToAlbum(filePath, getFileName(fileName, ".mp4"))
            }
        }
    }

    @JvmStatic
    private fun getFileName(fileName: String?, fmt: String): String {
        return if (TextUtils.isEmpty(fileName)) {
            System.currentTimeMillis().toString() + fmt
        } else {
            fileName!!
        }
    }

    @JvmStatic
    private fun saveImageToAlbum(filePath: String, fileName: String) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            return
        }
        val bitmap = loadBitmap(filePath) ?: return

        val contentValues = ContentValues()
        val context = AnalyticManager.manager.appContext()
        val targetPath = Environment.DIRECTORY_DCIM + "/" + FileType.DCIM_DIR
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 1)
        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, targetPath)
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/*")

        val contentUri = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        } else {
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        }
        val uri = context?.contentResolver?.insert(contentUri, contentValues)
            ?: return
        var outputStream: OutputStream? = null
        try {
            outputStream = context.contentResolver.openOutputStream(uri)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(uri, contentValues, null, null)
        } catch (e: Exception) {
            context.contentResolver.delete(uri, null, null)
            ZLog.e(e)
        } finally {
            try {
                bitmap.recycle()
                outputStream?.close()
            } catch (e: Exception) {
                ZLog.e(e)
            }
        }
    }

    @JvmStatic
    private fun loadBitmap(filePath: String?): Bitmap? {
        if (TextUtils.isEmpty(filePath)) {
            return null
        }
        return try {
            BitmapFactory.decodeFile(filePath!!)
        } catch (e: Exception) {
            ZLog.e(e)
            null
        }
    }

    @JvmStatic
    private fun saveGifToAlbum(filePath: String, fileName: String) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            return
        }
        val file = File(filePath)
        if (!file.exists()) {
            return
        }
        val contentValues = ContentValues()
        val context = AnalyticManager.manager.appContext()
        val targetPath = Environment.DIRECTORY_DCIM + "/" + FileType.DCIM_DIR
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 1)
        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, targetPath)
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/*")

        val contentUri = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        } else {
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        }
        val uri = context?.contentResolver?.insert(contentUri, contentValues)
            ?: return
        var outputStream: OutputStream? = null
        try {
            outputStream = context.contentResolver.openOutputStream(uri)
            Files.copy(file.toPath(), outputStream)
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(uri, contentValues, null, null)
        } catch (e: Exception) {
            context.contentResolver.delete(uri, null, null)
            ZLog.e(e)
        } finally {
            try {
                outputStream?.close()
            } catch (e: Exception) {
                ZLog.e(e)
            }
        }
    }

    @JvmStatic
    private fun saveVideoToAlbum(filePath: String, fileName: String) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            return
        }
        val file = File(filePath)
        if (!file.exists()) {
            return
        }
        val contentValues = ContentValues()
        val context = AnalyticManager.manager.appContext()
        val targetPath = Environment.DIRECTORY_DCIM + "/" + FileType.DCIM_DIR
        contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, fileName)
        contentValues.put(MediaStore.Video.Media.IS_PENDING, 1)
        contentValues.put(MediaStore.Video.Media.RELATIVE_PATH, targetPath)
        contentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")

        val contentUri = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        } else {
            MediaStore.Video.Media.INTERNAL_CONTENT_URI
        }
        val uri = context?.contentResolver?.insert(contentUri, contentValues)
            ?: return
        var outputStream: OutputStream? = null
        try {
            outputStream = context.contentResolver.openOutputStream(uri)
            Files.copy(file.toPath(), outputStream)
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(uri, contentValues, null, null)
        } catch (e: Exception) {
            context.contentResolver.delete(uri, null, null)
            ZLog.e(e)
        } finally {
            try {
                outputStream?.close()
            } catch (e: Exception) {
                ZLog.e(e)
            }
        }
    }
}