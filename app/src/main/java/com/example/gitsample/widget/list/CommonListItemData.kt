package com.example.gitsample.widget.list

import android.view.View.OnClickListener
import com.example.gitsample.base.PageType
import com.example.gitsample.system.file.FileShowType

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
        fun buildData(type: PageType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.title, type.info, null, 0, true, listener)
        }

        @JvmStatic
        fun buildData(type: FileShowType, listener: OnClickListener): CommonListItemData {
            return CommonListItemData(type.fileType, type.info, null, 0, false, listener)
        }
    }
}