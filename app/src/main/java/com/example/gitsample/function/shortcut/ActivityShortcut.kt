package com.example.gitsample.function.shortcut

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityShortcutBinding
import com.example.gitsample.utils.ZLog
import com.example.gitsample.utils.ZToast

class ActivityShortcut : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShortcut::class.java))
        }
    }

    private lateinit var binding: ActivityShortcutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        binding.toolbar.initShow(PageType.SHORTCUT.title)
        val clickListener = OnClickListener {
            when (it.id) {
                R.id.logo_shortcut -> {
                    if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                        createShortcut()
                    } else {
                        ZToast.show("不支持添加桌面快捷方式")
                    }
                }
                R.id.logo_dynamic -> {
                    if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                        createDynamicShortcut()
                    } else {
                        ZToast.show("不支持添加动态快捷方式")
                    }
                }
            }
        }
        binding.logoShortcut.setOnClickListener(clickListener)
        binding.logoDynamic.setOnClickListener(clickListener)
    }

    private fun createShortcut() {
        val intent = Intent(this, ActivityShortcutTarget::class.java)
        intent.action = Intent.ACTION_VIEW

        intent.putExtra(ShortcutConstants.KEY_SHORTCUT_NAME, ShortcutConstants.SHORTCUT_NAME)
        intent.putExtra(ShortcutConstants.KEY_SHORTCUT_ID, ShortcutConstants.SHORTCUT_ID)
        intent.putExtra(ShortcutConstants.SHORTCUT_BOOLEAN, true) // 是否可用
        intent.putExtra(ShortcutConstants.KEY_FROM_STRING, ShortcutConstants.KEY_FROM_SHORTCUT)

        val shortcutInfoCompat = ShortcutInfoCompat.Builder(this, ShortcutConstants.SHORTCUT_ID)
            .setShortLabel(ShortcutConstants.SHORTCUT_NAME) // 简述
            //.setLongLabel("") //长述
            .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_logo)) // 快捷方式图标
            .setIntent(intent) // 点击图标跳转的界面
            .setAlwaysBadged()
            .build()

        if (isExit(ShortcutConstants.SHORTCUT_ID)) {
            updateShortcut(shortcutInfoCompat.toShortcutInfo())
        } else {
            createShortcut(shortcutInfoCompat)
        }
    }

    private fun isExit(shortcutId: String): Boolean {
        val manager = getSystemService(ShortcutManager::class.java) ?: return false
        val pinnedShortcuts = manager.pinnedShortcuts
        if (pinnedShortcuts.isEmpty()) {
            return false
        }
        for (info in pinnedShortcuts) {
            if (info.id == shortcutId) {
                return true
            }
        }
        return false
    }

    private fun updateShortcut(shortcutInfo: ShortcutInfo) {
        val manager = getSystemService(ShortcutManager::class.java) ?: return
        val list = ArrayList<ShortcutInfo>()
        list.add(shortcutInfo)
        val result = manager.updateShortcuts(list)
        if (result) {
            ZToast.show("更新完成")
        }
    }

    private fun createShortcut(shortcutInfoCompat: ShortcutInfoCompat) {
        val bundle = Bundle()
        bundle.putString(
            ShortcutConstants.KEY_SHORTCUT_EXTRA_ID,
            shortcutInfoCompat.id
        )
        bundle.putString(
            ShortcutConstants.KEY_SHORTCUT_EXTRA_LABEL,
            shortcutInfoCompat.shortLabel.toString()
        )
        val sendIntent = Intent(ShortcutConstants.SHORTCUT_ACTION)
        sendIntent.component = ComponentName(this, ShortcutReceiver::class.java)
        sendIntent.putExtras(bundle)

        val flag = PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        val intentSender = PendingIntent.getBroadcast(this, 0, sendIntent, flag).intentSender
        val result =
            ShortcutManagerCompat.requestPinShortcut(this, shortcutInfoCompat, intentSender)
        ZLog.d("createShortcut result : $result")
    }

    private fun createDynamicShortcut() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            component = ComponentName(packageName, ActivityShortcutTarget::class.java.name)
        }

        val shortcutInfoCompat = ShortcutInfoCompat.Builder(this, ShortcutConstants.DYNAMIC_ID)
            .setShortLabel(ShortcutConstants.DYNAMIC_NAME)
            .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_logo))
            .setIntent(intent)
            .build()

        val shortcutInfo = shortcutInfoCompat.toShortcutInfo()
        if (isExit(ShortcutConstants.DYNAMIC_ID)) {
            updateShortcut(shortcutInfo)
        } else {
            addDynamicShortcut(shortcutInfoCompat)
        }
    }

    private fun addDynamicShortcut(shortcutInfoCompat: ShortcutInfoCompat) {
        val manager = getSystemService(ShortcutManager::class.java) ?: return
        val shortcutInfo = shortcutInfoCompat.toShortcutInfo()
        if (manager.dynamicShortcuts.isEmpty() || !manager.dynamicShortcuts.contains(shortcutInfo)) {
            val result = ShortcutManagerCompat.pushDynamicShortcut(this, shortcutInfoCompat)
            if (result) {
                ZToast.show("创建成功")
            } else {
                ZToast.show("创建失败")
            }
        } else {
            updateShortcut(shortcutInfo)
        }
    }
}