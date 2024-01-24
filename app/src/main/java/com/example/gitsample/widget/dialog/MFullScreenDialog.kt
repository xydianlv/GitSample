package com.example.gitsample.widget.dialog

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.example.gitsample.R

class MFullScreenDialog(context: Context) {

    private val alertDialog: AlertDialog = AlertDialog.Builder(context, R.style.full_screen_dialog).create()

    init {
        // 非必须的设置
        alertDialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(0))
        }
    }

    fun dismiss() {
        alertDialog.dismiss()
    }

    fun show(contentView: View) {
        if (alertDialog.isShowing) {
            alertDialog.dismiss()
        }
        if (contentView.context == null) {
            return
        }
        alertDialog.setView(contentView)
        alertDialog.show()

        alertDialog.window?.apply {
            val layoutParams = attributes
            //layoutParams.gravity = Gravity.BOTTOM
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            //decorView.setPadding(0, 0, 0, 0)
            attributes = layoutParams
        }
    }

    fun setOnDismissListener(listener: () -> Unit) {
        alertDialog.setOnDismissListener {
            listener.invoke()
        }
    }
}