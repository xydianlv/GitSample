package com.example.gitsample.widget.list

import android.view.View
import androidx.core.content.ContextCompat
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutCommonListItemHolderBinding
import com.example.multi.cell.IHolderCell

@BindHolder
class CommonListItemHolder : IHolderCell {

    private var binding: LayoutCommonListItemHolderBinding? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_common_list_item_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutCommonListItemHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is CommonListItemData || binding == null) {
            return
        }
        binding!!.apply {
            title.text = itemData.title
            if (itemData.info.isNullOrEmpty()) {
                info.visibility = View.GONE
            } else {
                info.visibility = View.VISIBLE
                info.text = itemData.info
            }
            if (itemData.alert.isNullOrEmpty()) {
                alert.visibility = View.GONE
            } else {
                alert.visibility = View.VISIBLE
                alert.text = itemData.alert
                alert.setTextColor(ContextCompat.getColor(root.context, itemData.alertColor))
            }
            arrow.visibility = if (itemData.arrow && itemData.alert.isNullOrEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        binding!!.root.setOnClickListener {
            itemData.listener?.onClick(it)
        }
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {
    }

}