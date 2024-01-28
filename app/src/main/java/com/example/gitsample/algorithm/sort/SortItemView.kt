package com.example.gitsample.algorithm.sort

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutSortItemViewBinding

/**
 * Created by wyyu on 2024/1/28.
 **/
class SortItemView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var binding: LayoutSortItemViewBinding? = null

    init {
        val view = View.inflate(context, R.layout.layout_sort_item_view, this)
        binding = LayoutSortItemViewBinding.bind(view)
    }

    fun initTitle(sortName: String) {
        binding?.title?.text = sortName
    }

    fun refreshResult(resultArray: Array<Int>, time: Long) {
        val strBuilder = StringBuilder()
        resultArray.forEachIndexed { index, value ->
            strBuilder.append(value.toString())
            if (index < resultArray.size - 1) {
                strBuilder.append("、")
            }
        }
        binding?.result?.text = strBuilder
        binding?.time?.text = StringBuilder(time.toString()).append("ms")

    }
}