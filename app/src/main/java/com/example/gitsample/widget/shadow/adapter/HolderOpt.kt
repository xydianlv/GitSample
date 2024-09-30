package com.example.gitsample.widget.shadow.adapter

import android.view.View
import android.widget.SeekBar
import com.example.expand.BindHolder
import com.example.gitsample.R
import com.example.gitsample.databinding.LayoutItemHolderOptBinding
import com.example.gitsample.widget.shadow.ShadowOptType
import com.example.gitsample.widget.shadow.adapter.entity.EntityOpt
import com.example.multi.cell.IHolderCell

@BindHolder
class HolderOpt : IHolderCell {

    private var binding: LayoutItemHolderOptBinding? = null

    override fun getHolderLayout(): Int {
        return R.layout.layout_item_holder_opt
    }

    override fun onCreateView(itemView: View) {
        binding = LayoutItemHolderOptBinding.bind(itemView)
    }

    override fun onBindCell(position: Int, itemData: Any?, vararg params: Any?) {
        if (itemData !is EntityOpt) {
            return
        }
        binding?.tvOptName?.text = itemData.optType.optName

        binding?.seekbar?.progress = getProgressByData(itemData)
        binding?.seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onRefreshProgress(progress = progress * 1.0f / 100, itemData)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //
            }
        })
    }

    override fun onUpdateCell(updateType: Int, vararg params: Any?) {
        //
    }

    private fun getProgressByData(itemData: EntityOpt): Int {
        return when (itemData.optType) {
            ShadowOptType.OPT_ALPHA -> {
                (itemData.optData.shadowAlpha * 100).toInt()
            }

            ShadowOptType.OPT_RADIUS -> {
                (itemData.optData.viewRadius / 90.0f).toInt()
            }

            ShadowOptType.OPT_ELEVATION -> {
                itemData.optData.shadowElevation.toInt()
            }
        }
    }

    private fun onRefreshProgress(progress: Float, itemData: EntityOpt) {
        when (itemData.optType) {
            ShadowOptType.OPT_ALPHA -> {
                itemData.optData.shadowAlpha = progress
            }

            ShadowOptType.OPT_RADIUS -> {
                itemData.optData.viewRadius = progress * 90
            }

            ShadowOptType.OPT_ELEVATION -> {
                itemData.optData.shadowElevation = progress * 100
            }
        }
        itemData.callback.callback()
    }
}