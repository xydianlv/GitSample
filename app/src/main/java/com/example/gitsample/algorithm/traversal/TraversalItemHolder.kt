package com.example.gitsample.algorithm.traversal

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutTraversalItemHolderBinding
import com.example.multi.cell.IHolderCell

/**
 * Created by wyyu on 2024/2/17.
 **/
@BindHolder
class TraversalItemHolder : IHolderCell {

    companion object {

        private const val TEXT_DIVIDE = "、"
        private const val TEXT_EMPTY = ""
    }

    private var binding: LayoutTraversalItemHolderBinding? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_traversal_item_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutTraversalItemHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is TraversalItemData) {
            return
        }
        binding?.apply {
            title.text = itemData.traversalType
            val strBuilder = StringBuilder(TEXT_EMPTY)
            itemData.resultList.forEach {
                strBuilder.append(it.nodeValue)
                strBuilder.append(TEXT_DIVIDE)
            }
            result.text = strBuilder.substring(0, strBuilder.length)
        }
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {

    }
}