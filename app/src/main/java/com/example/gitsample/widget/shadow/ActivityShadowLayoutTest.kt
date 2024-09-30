package com.example.gitsample.widget.shadow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.PageType
import com.example.base.listener.ZCallback
import com.example.gitsample.databinding.ActivityShadowLayoutTestBinding
import com.example.gitsample.widget.shadow.adapter.HolderOpt
import com.example.gitsample.widget.shadow.adapter.HolderShadow
import com.example.gitsample.widget.shadow.adapter.entity.EntityOpt
import com.example.gitsample.widget.shadow.adapter.entity.EntityOptData
import com.example.gitsample.widget.shadow.adapter.entity.EntityShadow
import com.example.multi.adapter.MultiAdapter
import com.example.multi.cell.ClassCellManager
import com.example.widget.activity.BaseActivity

class ActivityShadowLayoutTest : BaseActivity<ActivityShadowLayoutTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShadowLayoutTest::class.java))
        }
    }

    private var adapter: MultiAdapter<Class<out Any>, Any>? = null

    override fun getViewBinding(): ActivityShadowLayoutTestBinding {
        return ActivityShadowLayoutTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initList()
        loadList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.SHADOW_LAYOUT.title)
    }

    private fun initList() {
        adapter = MultiAdapter(ClassCellManager(), binding.list)
        adapter?.registerItem(EntityShadow::class.java, HolderShadow())
        adapter?.registerItem(EntityOpt::class.java, HolderOpt())
        binding.list.adapter = adapter

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.itemAnimator = null
        binding.list.animation = null
    }

    private fun loadList() {
        val optData = EntityOptData()

        val dataList = ArrayList<Any>()
        dataList.add(EntityShadow(optData))
        val callback: ZCallback.ZCallbackF = object : ZCallback.ZCallbackF {
            override fun callback() {
                adapter?.notifyItemChanged(0)
            }
        }
        dataList.add(EntityOpt(ShadowOptType.OPT_ALPHA, optData, callback))
        dataList.add(EntityOpt(ShadowOptType.OPT_ELEVATION, optData, callback))
        dataList.add(EntityOpt(ShadowOptType.OPT_RADIUS, optData, callback))

        adapter?.getItemList()?.clear()
        adapter?.getItemList()?.addAll(dataList)
        adapter?.notifyItemRangeChanged(0, dataList.size)
    }
}