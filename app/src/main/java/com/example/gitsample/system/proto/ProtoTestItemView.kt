package com.example.gitsample.system.proto

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutProtoTestItemViewBinding
import com.example.utils.GCallback02

class ProtoTestItemView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val binding: LayoutProtoTestItemViewBinding

    private var onSelectedListener: GCallback02<Int, String?>? = null
    private var infoValue: String? = null
    private var onIndex: Int = 0

    private var onSelected: Boolean = false

    init {
        val view = View.inflate(context, R.layout.layout_proto_test_item_view, this)
        binding = LayoutProtoTestItemViewBinding.bind(view)

        binding.tvAdd.setOnClickListener {
            if (onSelected) {
                onSelectedListener?.callback(onIndex, null)
            } else {
                onSelectedListener?.callback(onIndex, infoValue)
            }
            onSelected = !onSelected
            refreshStatus()
        }
        refreshStatus()
    }

    private fun refreshStatus() {
        val infoValue = if (onSelected) {
            "移除"
        } else {
            "添加"
        }
        val textColor = if (onSelected) {
            com.example.resource.R.color.color_df2f2f
        } else {
            com.example.resource.R.color.color_alert
        }
        binding.tvAdd.text = infoValue
        binding.tvAdd.setTextColor(ContextCompat.getColor(context, textColor))
    }

    fun initItemShow(index: Int, infoValue: String, hideLine: Boolean) {
        this.onIndex = index
        this.infoValue = infoValue

        binding.tvInfo.text = infoValue
        binding.vLine.visibility = if (hideLine) {
            GONE
        } else {
            VISIBLE
        }
    }

    fun setOnSelectedListener(onSelectedListener: GCallback02<Int, String?>) {
        this.onSelectedListener = onSelectedListener
    }

    fun onClearSelected() {
        onSelected = false
        refreshStatus()
    }
}