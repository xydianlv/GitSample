package com.example.gitsample.algorithm.sort

import android.view.View
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutSortItemHolderBinding
import com.example.multi.cell.IHolderCell

/**
 * Created by wyyu on 2024/1/29.
 **/
@BindHolder
class SortItemHolder : IHolderCell {

    companion object {

        private const val TEXT_RESULT = "Result..."
        private const val TEXT_DIVIDE = "、"
        private const val TEXT_EMPTY = ""
        private const val TEXT_MS = "ms"
    }

    private var binding: LayoutSortItemHolderBinding? = null
    override fun getHolderLayout(): Int {
        return R.layout.layout_sort_item_holder
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutSortItemHolderBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is SortItemData) {
            return
        }
        binding?.apply {
            title.text = itemData.sortType

            if (itemData.resultList.isEmpty()) {
                time.text = TEXT_EMPTY
                result.text = TEXT_RESULT
            } else {
                time.text = StringBuilder(itemData.time.toString()).append(TEXT_MS)
                val strBuilder = StringBuilder()
                itemData.resultList.forEachIndexed { index, value ->
                    strBuilder.append(value.toString())
                    if (index < itemData.resultList.size - 1) {
                        strBuilder.append(TEXT_DIVIDE)
                    }
                }
                result.text = strBuilder
            }
        }
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {

    }
}