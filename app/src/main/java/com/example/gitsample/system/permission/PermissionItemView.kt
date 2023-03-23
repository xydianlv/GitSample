package com.example.gitsample.system.permission

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutPermissionItemViewBinding
import com.example.gitsample.utils.ZToast

class PermissionItemView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var binding: LayoutPermissionItemViewBinding? = null
    private var item: PermissionItemType? = null

    init {
        val view = View.inflate(context, R.layout.layout_permission_item_view, this)
        binding = LayoutPermissionItemViewBinding.bind(view)

        this.setOnClickListener {
            if (item == null) {
                return@setOnClickListener
            }
            if (PermissionChecker.hasPermission(item!!.permission)) {
                ZToast.show("已获得该权限")
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        PermissionChecker.requestPermission(item!!, object : PermissionRequestCallback {
            override fun onDenied() {
                ZToast.show("权限获取失败")
            }

            override fun onGranted() {
                ZToast.show("已获得该权限")
            }
        })
    }

    fun initItemShow(itemType: PermissionItemType) {
        binding?.title?.text = itemType.title
        binding?.info?.text = itemType.info

        item = itemType
    }

    fun refreshStatus(hasOpen: Boolean) {
        binding?.alert?.setTextColor(
            if (hasOpen) {
                ContextCompat.getColor(context, R.color.ct_3)
            } else {
                ContextCompat.getColor(context, R.color.color_alert)
            }
        )
        binding?.alert?.text = if (hasOpen) {
            resources.getText(R.string.permission_has_request)
        } else {
            resources.getText(R.string.permission_to_request)
        }
    }
}