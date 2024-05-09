package com.example.widget.list

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.NonNull
import com.example.base.PageType

class CommonListItemData(
    val title: String,
    var info: String?,
    var alert: String?,
    var alertColor: Int,
    var arrow: Boolean,
    var listener: View.OnClickListener?
) {

    companion object {

        @JvmStatic
        fun obj(pageType: PageType): CommonListItemData {
            return obj(pageType.title, pageType.info).arrow(true)
        }

        @JvmStatic
        fun obj(title: String, info: String): CommonListItemData {
            return CommonListItemData(title, info, null, 0, false, null)
        }
    }

    fun arrow(arrow: Boolean): CommonListItemData {
        this.arrow = arrow
        return this
    }

    fun alert(alert: String, @ColorRes alertColor: Int): CommonListItemData {
        this.alert = alert
        this.alertColor = alertColor;
        return this
    }

    fun clickListener(listener: View.OnClickListener): CommonListItemData {
        this.listener = listener
        return this
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