package com.example.gitsample.widget.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AlertDialog
import com.example.gitsample.R
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityMyDialogListBinding
import com.example.gitsample.databinding.LayoutMyAlertDialogBinding
import com.example.widget.view.ZToast
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityMyDialogList : BaseActivity<ActivityMyDialogListBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityMyDialogList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityMyDialogListBinding {
        return ActivityMyDialogListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.DIALOG.title)
    }

    private fun initList() {
        binding.list.addItem(buildItemData(DialogType.ALERT_DIALOG_SYSTEM) {
            showAlertDialog()
        }).addItem(buildItemData(DialogType.ALERT_DIALOG_MY) {
            showMAlertDialog()
        }).addItem(buildItemData(DialogType.FULL_SCREEN_DIALOG) {
            showMFullScreenDialog()
        }).addItem(buildItemData(DialogType.INPUT_DIALOG) {

        }).refreshList()
    }

    private fun buildItemData(
        dialogType: DialogType,
        listener: OnClickListener
    ): CommonListItemData {
        return CommonListItemData.obj(dialogType.title, dialogType.info).clickListener(listener)
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Title")
            .setMessage("This is a alert msg")
            .setPositiveButton(
                "Positive"
            ) { _, _ -> ZToast.show("on positive click") }
            .setNegativeButton(
                "negative"
            ) { _, _ -> ZToast.show("on negative click") }
            .setNeutralButton(
                "neutral"
            ) { _, _ -> ZToast.show("on neutral click") }
            .setOnDismissListener { ZToast.show("onDismissListener") }
            .show()
    }

    private fun showMAlertDialog() {
        MAlertDialog(this)
            .title("Title")
            .msg("This is a alert msg")
            .cancel("cancel") { ZToast.show("on click cancel") }
            .confirm("confirm") { ZToast.show("on click confirm") }
            .setOnDismissListener { ZToast.show("onDismiss") }
            .show()
    }

    private fun showMFullScreenDialog() {
        val view = View.inflate(this, R.layout.layout_my_alert_dialog, null)
        val binding = LayoutMyAlertDialogBinding.bind(view)

        val fullScreenDialog = MFullScreenDialog(context = this)
        fullScreenDialog.setOnDismissListener {
            ZToast.show("onDismiss")
        }
        binding.cancel.setOnClickListener {
            fullScreenDialog.dismiss()
        }
        binding.confirm.setOnClickListener {
            fullScreenDialog.dismiss()
        }
        fullScreenDialog.show(binding.root)
    }
}