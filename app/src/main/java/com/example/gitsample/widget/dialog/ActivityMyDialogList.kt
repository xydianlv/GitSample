package com.example.gitsample.widget.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityMyDialogListBinding
import com.example.gitsample.databinding.LayoutMyAlertDialogBinding
import com.example.gitsample.utils.ZToast
import com.example.gitsample.widget.list.CommonListItemData

class ActivityMyDialogList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityMyDialogList::class.java))
        }
    }

    private lateinit var binding: ActivityMyDialogListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDialogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.DIALOG.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(DialogType.ALERT_DIALOG_SYSTEM) {
            showAlertDialog()
        }).addItem(CommonListItemData.buildData(DialogType.ALERT_DIALOG_MY) {
            showMAlertDialog()
        }).addItem(CommonListItemData.buildData(DialogType.FULL_SCREEN_DIALOG) {
            showMFullScreenDialog()
        }).addItem(CommonListItemData.buildData(DialogType.INPUT_DIALOG) {

        }).refreshList()
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