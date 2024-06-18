package com.example.gitsample.canvas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityCanvasListBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityCanvasList : BaseActivity<ActivityCanvasListBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityCanvasList::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initList()
    }

    override fun getViewBinding(): ActivityCanvasListBinding {
        return ActivityCanvasListBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.CANVAS.title)
    }

    private fun initList() {
        binding.list.addItem(CommonListItemData.obj(PageType.CANVAS_FIGURE).clickListener {
            ActivityCanvasShow.open(this, PageType.CANVAS_FIGURE)
        }).addItem(CommonListItemData.obj(PageType.CANVAS_TEXT).clickListener {
            ActivityCanvasShow.open(this, PageType.CANVAS_TEXT)
        }).addItem(CommonListItemData.obj(PageType.CANVAS_PATH).clickListener {
            ActivityCanvasShow.open(this, PageType.CANVAS_PATH)
        }).addItem(CommonListItemData.obj(PageType.CANVAS_IMAGE).clickListener {
            ActivityCanvasShow.open(this, PageType.CANVAS_IMAGE)
        }).addItem(CommonListItemData.obj(PageType.CANVAS_SHADOW).clickListener {
            ActivityCanvasShow.open(this, PageType.CANVAS_SHADOW)
        }).addItem(CommonListItemData.obj(PageType.CANVAS_X_FER_MODE).clickListener {

        }).refreshList()
    }
}