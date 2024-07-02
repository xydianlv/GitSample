package com.example.gitsample.canvas.path

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutCanvasPathSwitchBinding
import com.example.utils.UIUtils

class CanvasPathSwitchLayout : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var binding: LayoutCanvasPathSwitchBinding? = null

    init {
        val view = View.inflate(context, R.layout.layout_canvas_path_switch, this)
        binding = LayoutCanvasPathSwitchBinding.bind(view)

        binding?.vClickLeft?.setOnClickListener {
            onSelectLeft()
        }
        binding?.vClickRight?.setOnClickListener {
            onSelectRight()
        }
    }

    private fun onSelectLeft() {
        binding?.cpSwitch?.refreshSelectedLeft(true)
        (binding?.tvLeftTitle?.layoutParams as MarginLayoutParams).marginEnd = UIUtils.dpToPx(16.0f)
        (binding?.tvRightTitle?.layoutParams as MarginLayoutParams).marginStart = UIUtils.dpToPx(32.0f)
    }

    private fun onSelectRight() {
        binding?.cpSwitch?.refreshSelectedLeft(false)
        (binding?.tvLeftTitle?.layoutParams as MarginLayoutParams).marginEnd = UIUtils.dpToPx(32.0f)
        (binding?.tvRightTitle?.layoutParams as MarginLayoutParams).marginStart = UIUtils.dpToPx(16.0f)
    }
}