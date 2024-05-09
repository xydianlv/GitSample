package com.example.widget.log

import android.view.View
import com.example.expand.BindHolder
import com.example.multi.cell.IHolderCell
import com.example.utils.TimeUtils
import com.example.widget.R
import com.example.widget.databinding.LayoutLogListItemHolderBinding

@BindHolder
class LogListItemHolder : IHolderCell {

    private var binding: LayoutLogListItemHolderBinding? = null
    override fun getHolderLayout(): Int {
        return R.layout.layout_log_list_item_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutLogListItemHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (binding == null || itemData !is String) {
            return
        }
        val stringBuilder = StringBuilder(TimeUtils.getCurrentFormatTime())
            .append(" : ")
            .append(itemData)
        binding!!.text.text = stringBuilder.toString()
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {

    }
}