package com.example.gitsample.widget.list

import android.view.View.OnClickListener
import com.example.gitsample.base.PageType
import com.example.gitsample.system.file.FileShowType
import com.example.gitsample.system.permission.PermissionItemType
import com.example.gitsample.system.screen.ScreenType
import com.example.gitsample.widget.dialog.DialogType

class CommonListItemData(
    val title: String,
    var info: String?,
    var alert: String?,
    var alertColor: Int,
    var arrow: Boolean,
    var listener: OnClickListener?
) {

    companion object {

        @JvmStatic
        fun buildData(title: String, info: String, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(title, info, null, 0, true, listener)
        }

        @JvmStatic
        fun buildData(type: PageType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.title, type.info, null, 0, true, listener)
        }

        @JvmStatic
        fun buildData(type: FileShowType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.fileType, type.info, null, 0, false, listener)
        }

        @JvmStatic
        fun buildData(type: PermissionItemType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.title, type.info, null, 0, false, listener)
        }

        @JvmStatic
        fun buildData(type: ScreenType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.title, type.info, null, 0, false, listener)
        }

        @JvmStatic
        fun buildData(type: DialogType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.title, type.info, null, 0, true, listener)
        }
    }

    fun checkAndRefresh(data: CommonListItemData): Boolean {
        if (data.title != title || data.info != info) {
            return false
        }
        alert = data.alert
        alertColor = data.alertColor
        arrow = data.arrow
        listener = data.listener
        return true
    }
}