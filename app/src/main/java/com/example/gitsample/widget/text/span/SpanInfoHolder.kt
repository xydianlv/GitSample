package com.example.gitsample.widget.text.span

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutSpanInfoHolderBinding
import com.example.multi.cell.IHolderCell

@BindHolder
class SpanInfoHolder : IHolderCell {

    private var binding: LayoutSpanInfoHolderBinding? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_span_info_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutSpanInfoHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is SpanInfoData) {
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