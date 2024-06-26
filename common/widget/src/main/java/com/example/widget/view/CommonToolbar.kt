package com.example.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.widget.R
import com.example.widget.databinding.LayoutCommonToolbarBinding

class CommonToolbar : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var binding: LayoutCommonToolbarBinding? = null

    init {
        val view = View.inflate(context, R.layout.layout_common_toolbar, this)
        binding = LayoutCommonToolbarBinding.bind(view)
    }

    fun initShow(title: String) {
        binding?.title?.text = title
    }
}