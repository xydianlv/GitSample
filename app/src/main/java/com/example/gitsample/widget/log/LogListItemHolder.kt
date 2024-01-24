package com.example.gitsample.widget.log

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutLogListItemHolderBinding
import com.example.gitsample.utils.TimeUtils
import com.example.multi.cell.IHolderCell

/**
 * Created by wyyu on 2024/1/24.
 **/

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