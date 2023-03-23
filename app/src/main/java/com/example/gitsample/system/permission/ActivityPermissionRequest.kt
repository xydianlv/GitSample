package com.example.gitsample.system.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.lang.ref.WeakReference

class ActivityPermissionRequest : AppCompatActivity() {

    companion object {

        private const val KEY_PERMISSION = "key_permission"
        private const val REQUEST_CODE = 1001

        @JvmStatic
        fun open(context: Context, permission: String) {
            val intent = Intent(context, ActivityPermissionRequest::class.java)
            intent.putExtra(KEY_PERMISSION, permission)
            context.startActivity(intent)
        }

        private var weakReference: WeakReference<PermissionRequestCallback>? = null

        @JvmStatic
        fun setRequestCallback(callback: PermissionRequestCallback) {
            weakReference = WeakReference(callback)
        }
    }

    private var permission: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWindow()
        requestPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (weakReference != null) {
            weakReference?.clear()
            weakReference = null
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && permissions.contains(permission)) {
            if (grantResults[permissions.indexOf(permission)] == PackageManager.PERMISSION_GRANTED) {
                weakReference?.get()?.onGranted()
            } else {
                weakReference?.get()?.onDenied()
            }
        } else {
            weakReference?.get()?.onDenied()
        }
        finish()
    }

    private fun initWindow() {
        window.attributes.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        window.attributes.alpha = 0.0f
    }

    private fun requestPermission() {
        permission = intent.getStringExtra(KEY_PERMISSION)
        if (permission.isNullOrEmpty()) {
            weakReference?.get()?.onDenied()
            finish()
            return
        }
        if (PermissionChecker.hasPermission(permission!!)) {
            weakReference?.get()?.onGranted()
            finish()
            return
        }
        ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CODE)
    }
}