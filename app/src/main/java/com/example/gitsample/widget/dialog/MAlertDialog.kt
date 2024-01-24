package com.example.gitsample.widget.dialog

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutMyAlertDialogBinding

class MAlertDialog(val context: Context) {

    private val alertDialog: AlertDialog = AlertDialog.Builder(context).create()

    private var binding: LayoutMyAlertDialogBinding? = null

    init {
        val contentView = View.inflate(context, R.layout.layout_my_alert_dialog, null)
        binding = LayoutMyAlertDialogBinding.bind(contentView)

        alertDialog.setView(contentView)
    }

    fun title(title: String): MAlertDialog {
        binding?.title?.text = title
        return this
    }

    fun msg(msg: String): MAlertDialog {
        binding?.msg?.text = msg
        return this
    }

    fun cancel(cancelInfo: String, clickListener: View.OnClickListener? = null): MAlertDialog {
        binding?.cancel?.text = cancelInfo
        binding?.cancel?.setOnClickListener {
            clickListener?.onClick(it)
            alertDialog.dismiss()
        }
        return this
    }

    fun confirm(confirmInfo: String, clickListener: View.OnClickListener? = null): MAlertDialog {
        binding?.confirm?.text = confirmInfo
        binding?.confirm?.setOnClickListener {
            clickListener?.onClick(it)
            alertDialog.dismiss()
        }
        return this
    }

    fun setOnDismissListener(onDismiss: () -> Unit): MAlertDialog {
        alertDialog.setOnDismissListener {
            onDismiss.invoke()
        }
        return this
    }

    fun show() {
        if (alertDialog.isShowing) {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}