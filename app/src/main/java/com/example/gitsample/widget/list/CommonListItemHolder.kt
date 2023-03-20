package com.example.gitsample.widget.list

import android.view.View
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
        if (itemData !is CommonListItemData) {
            return
        }
        binding?.title?.text = itemData.title
        if (itemData.info.isNullOrEmpty()) {
            binding?.info?.visibility = View.GONE
        } else {
            binding?.info?.visibility = View.VISIBLE
            binding?.info?.text = itemData.info
        }
        binding?.root?.setOnClickListener {
            itemData.listener.onClick(it)
        }
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {
    }

}