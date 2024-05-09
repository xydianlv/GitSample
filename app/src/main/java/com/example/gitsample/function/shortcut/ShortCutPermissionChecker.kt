package com.example.gitsample.function.shortcut

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import com.example.utils.ZLog

object ShortCutPermissionChecker {

    /**
     * 判断是否有创建桌面快捷方式的权限
     */
    @JvmStatic
    fun checkPermission(context: Context): Int {
        when (Build.MANUFACTURER.lowercase()) {
            "huawei" -> {
                return checkOnEMUI(context)
            }
            "xiaomi" -> {
                return checkOnMIUI(context)
            }
            "oppo" -> {
                return checkOnOPPO(context)
            }
            "vivo" -> {
                return checkOnVIVO(context)
            }
            "samsung", "meizu" -> {
                return ShortcutConstants.PERMISSION_GRANTED
            }
        }
        return ShortcutConstants.PERMISSION_UNKNOWN
    }

    /**
     * 华为机型判断
     */
    @JvmStatic
    private fun checkOnEMUI(context: Context): Int {
        val intent = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
        try {
            val permissionManager = Class.forName("com.huawei.hsm.permission.PermissionManager")
            val canSendBroadcast = permissionManager.getDeclaredMethod(
                "canSendBroadcast",
                Context::class.java,
                Intent::class.java
            )
            val invoke = canSendBroadcast.invoke(permissionManager, context, intent)
                ?: return ShortcutConstants.PERMISSION_UNKNOWN
            return if (invoke as Boolean) {
                ShortcutConstants.PERMISSION_GRANTED
            } else {
                ShortcutConstants.PERMISSION_DENIED
            }
        } catch (e: Exception) {
            ZLog.e(e)
            return ShortcutConstants.PERMISSION_UNKNOWN
        }
    }

    /**
     * VIVO机型判断
     */
    @JvmStatic
    private fun checkOnVIVO(context: Context): Int {
        val contentResolver = context.contentResolver ?: return ShortcutConstants.PERMISSION_UNKNOWN
        var query: Cursor? = null
        try {
            val uri = Uri.parse("content://com.bbk.launcher2.settings/favorites")
            query = contentResolver.query(uri, null, null, null, null)
            if (query == null) {
                return ShortcutConstants.PERMISSION_UNKNOWN
            }
            val packageName = getAppName(context)
            while (query.moveToNext()) {
                val titleByQueryLauncher = query.getString(query.getColumnIndexOrThrow("title"))
                if (packageName == titleByQueryLauncher) {
                    when (query.getInt(query.getColumnIndexOrThrow("shortcutPermission"))) {
                        1, 17 -> {
                            return ShortcutConstants.PERMISSION_DENIED
                        }
                        16 -> {
                            return ShortcutConstants.PERMISSION_GRANTED
                        }
                        18 -> {
                            return ShortcutConstants.PERMISSION_ASK
                        }
                    }
                }
            }
        } catch (e: Exception) {
            ZLog.e(e)
        } finally {
            query?.close()
        }
        return ShortcutConstants.PERMISSION_UNKNOWN
    }

    /**
     * 小米机型判断
     */
    @JvmStatic
    private fun checkOnMIUI(context: Context): Int {
        try {
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val pkg = context.applicationContext.packageName
            val uid = context.applicationInfo.uid
            val appOpsClass = Class.forName(AppOpsManager::class.java.name)
            val checkOpNoThrowMethod = appOpsClass.getDeclaredMethod(
                "checkOpNoThrow",
                Integer.TYPE,
                Integer.TYPE,
                String::class.java
            )
            val invoke = checkOpNoThrowMethod.invoke(appOps, 10017, uid, pkg)
                ?: return ShortcutConstants.PERMISSION_UNKNOWN
            when (invoke.toString()) {
                "0" -> {
                    return ShortcutConstants.PERMISSION_GRANTED
                }
                "1" -> {
                    return ShortcutConstants.PERMISSION_DENIED
                }
                "5" -> {
                    return ShortcutConstants.PERMISSION_ASK
                }
            }
        } catch (e: Exception) {
            ZLog.e(e)
        }
        return ShortcutConstants.PERMISSION_UNKNOWN
    }

    /**
     * OPPO机型判断
     */
    @JvmStatic
    private fun checkOnOPPO(context: Context): Int {
        val contentResolver = context.contentResolver ?: return ShortcutConstants.PERMISSION_UNKNOWN
        var query: Cursor? = null
        try {
            val uri = Uri.parse("content://settings/secure/launcher_shortcut_permission_settings")
            query = contentResolver.query(uri, null, null, null, null)
            if (query == null) {
                return ShortcutConstants.PERMISSION_UNKNOWN
            }

            val pkg = context.applicationContext.packageName
            while (query.moveToNext()) {
                val columnIndex = query.getColumnIndex("value")
                if (columnIndex < 0) {
                    continue
                }
                val value = query.getString(columnIndex)
                if (TextUtils.isEmpty(value)) {
                    continue
                }
                if (value.contains("$pkg, 1")) {
                    return ShortcutConstants.PERMISSION_GRANTED
                }
                if (value.contains("$pkg, 0")) {
                    return ShortcutConstants.PERMISSION_DENIED
                }
            }
        } catch (e: Exception) {
            ZLog.e(e)
        } finally {
            query?.close()
        }
        return ShortcutConstants.PERMISSION_UNKNOWN
    }

    @JvmStatic
    private fun getAppName(context: Context): String {
        return try {
            val packageManager = context.packageManager
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager?.getPackageInfo(
                    context.applicationContext.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                null
            }
            packageInfo?.applicationInfo?.loadLabel(packageManager)?.toString() ?: ""
        } catch (e: Exception) {
            ""
        }
    }
}