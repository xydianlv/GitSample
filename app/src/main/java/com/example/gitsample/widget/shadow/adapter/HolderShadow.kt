package com.example.gitsample.widget.shadow.adapter

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutItemHolderShadowBinding
import com.example.gitsample.widget.shadow.VShadowHelper
import com.example.gitsample.widget.shadow.adapter.entity.EntityShadow
import com.example.multi.cell.IHolderCell

@BindHolder
class HolderShadow : IHolderCell {

    private var binding: LayoutItemHolderShadowBinding? = null

    private var optHelper: VShadowHelper? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_item_holder_shadow
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutItemHolderShadowBinding.bind(itemView)
        if (binding?.clTest != null) {
            optHelper = VShadowHelper(binding!!.clTest)
        }
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is EntityShadow) {
            return
        }
        optHelper?.setShadowColor(itemData.optData.shadowColor)
        optHelper?.setShadowAlpha(itemData.optData.shadowAlpha)
        optHelper?.setShadowElevation(itemData.optData.shadowElevation)
        optHelper?.setRadius(itemData.optData.viewRadius)
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {
        //
    }
}