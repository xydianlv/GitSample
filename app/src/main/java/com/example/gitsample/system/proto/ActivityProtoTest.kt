package com.example.gitsample.system.proto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.base.PageType
import com.example.resource.R
import com.example.gitsample.databinding.ActivityProtoTestBinding
import com.example.widget.activity.BaseVMActivity

class ActivityProtoTest : BaseVMActivity<ActivityProtoTestBinding, ProtoTestVM>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityProtoTest::class.java))
        }

        private const val ITEM_COUNT = 5
    }

    private val itemViewList: ArrayList<ProtoTestItemView> = ArrayList()

    override fun getVMClass(): Class<ProtoTestVM> {
        return ProtoTestVM::class.java
    }

    override fun getViewBinding(): ActivityProtoTestBinding {
        return ActivityProtoTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initView()
        initVM()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.PROTO_TEST.title)
    }

    private fun initView() {
        itemViewList.add(binding.item00)
        itemViewList.add(binding.item01)
        itemViewList.add(binding.item02)
        itemViewList.add(binding.item03)
        itemViewList.add(binding.item04)

        itemViewList.forEach {
            it.setOnSelectedListener { valueT, valueK ->
                viewModel.cacheOnSelectedValue(valueT, valueK)
                refreshSaveShow()
            }
        }

        binding.tvSave.text = "保存"
        binding.tvSave.setOnClickListener {
            viewModel.trySaveValueToFile()
            refreshSaveShow()
            itemViewList.forEach {
                it.onClearSelected()
            }
        }
        refreshSaveShow()

        binding.tvLoad.text = "加载"
        binding.tvLoad.setOnClickListener {
            viewModel.tryLoadInfoList()
        }
    }

    private fun refreshSaveShow() {
        if (viewModel.isEmptyCacheValue()) {
            binding.tvSave.setTextColor(ContextCompat.getColor(this, R.color.ct_3))
            binding.tvSave.isEnabled = false
        } else {
            binding.tvSave.setTextColor(ContextCompat.getColor(this, R.color.color_alert))
            binding.tvSave.isEnabled = true
        }
    }

    private fun initVM() {
        viewModel.infoListLiveData.observe(this) {
            initItemListShow(it)
        }
        viewModel.resultListLiveData.observe(this) {
            initResultListShow(it)
        }
        viewModel.loadInfoList(resources)
    }

    private fun initItemListShow(infoList: List<String>?) {
        var count = 0
        infoList?.forEachIndexed { index, value ->
            if (index >= ITEM_COUNT) {
                return
            }
            itemViewList[index].initItemShow(index, value, index == ITEM_COUNT - 1)
            itemViewList[index].visibility = View.VISIBLE
            count++
        }
    }

    private fun initResultListShow(infoList: List<String>?) {
        binding.llContainerBottom.removeAllViews()
        val padding14 = resources.getDimension(R.dimen.padding_14).toInt()
        val padding10 = resources.getDimension(R.dimen.padding_10).toInt()
        infoList?.forEach {
            val textView = AppCompatTextView(this)
            textView.setTextColor(ContextCompat.getColor(this, R.color.ct_1))
            textView.setPadding(padding14, padding10, padding14, padding10)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            textView.text = it
            binding.llContainerBottom.addView(textView)
        }
    }
}