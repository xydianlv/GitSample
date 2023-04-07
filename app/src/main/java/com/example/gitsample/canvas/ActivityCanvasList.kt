package com.example.gitsample.canvas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityCanvasListBinding
import com.example.gitsample.widget.list.CommonListItemData

class ActivityCanvasList : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityCanvasList::class.java))
        }
    }

    private lateinit var binding: ActivityCanvasListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanvasListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.CANVAS.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.buildData(PageType.CANVAS_FIGURE) {

        }).addItem(CommonListItemData.buildData(PageType.CANVAS_TEXT) {

        }).addItem(CommonListItemData.buildData(PageType.CANVAS_PATH) {

        }).addItem(CommonListItemData.buildData(PageType.CANVAS_IMAGE) {

        }).addItem(CommonListItemData.buildData(PageType.CANVAS_X_FER_MODE) {

        }).refreshList()
    }
}