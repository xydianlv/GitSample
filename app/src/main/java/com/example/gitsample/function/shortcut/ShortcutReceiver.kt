package com.example.gitsample.function.shortcut

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.widget.view.ZToast

class ShortcutReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (ShortcutConstants.SHORTCUT_ACTION != intent?.action) {
            return
        }
        val id = intent.getStringExtra(ShortcutConstants.KEY_SHORTCUT_EXTRA_ID)
        val label = intent.getStringExtra(ShortcutConstants.KEY_SHORTCUT_EXTRA_LABEL)

        if (ShortcutConstants.SHORTCUT_ID == id && ShortcutConstants.SHORTCUT_NAME == label) {
            ZToast.show("创建成功")
        }
    }
}