package com.example.gitsample.system.info

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutSystemInfoHolderBinding
import com.example.multi.cell.IHolderCell

@BindHolder
class SystemInfoHolder : IHolderCell {

    private var binding: LayoutSystemInfoHolderBinding? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_system_info_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutSystemInfoHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is SystemInfoData) {
            return
        }
        binding?.apply {
            title.text = itemData.key
            info.text = itemData.value
        }
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {
    }
}