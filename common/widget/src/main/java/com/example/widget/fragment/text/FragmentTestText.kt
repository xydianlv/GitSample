package com.example.widget.fragment.text

import android.view.LayoutInflater
import com.example.utils.ColorUtils
import com.example.utils.ViewUtils
import com.example.widget.databinding.FragmentTestTextBinding
import com.example.widget.fragment.BaseVMFragment

class FragmentTestText : BaseVMFragment<FragmentTestTextBinding, FragTextVM>() {

    companion object {

        @JvmStatic
        fun fragment(params: FragTextParams?): FragmentTestText {
            val fragment = FragmentTestText()
            fragment.arguments = params?.build()
            return fragment
        }
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentTestTextBinding {
        return FragmentTestTextBinding.inflate(inflater)
    }

    override fun getViewModelClass(): Class<FragTextVM> {
        return FragTextVM::class.java
    }

    override fun initData() {
        super.initData()
        viewModel.initData(arguments)
    }

    override fun initView() {
        super.initView()
        binding.text.text = viewModel.params?.textValue()
        binding.root.setBackgroundColor(ColorUtils.placeHolderColor(requireContext()))

        ViewUtils.setText(binding.btn, viewModel.params?.btnTextValue())
        binding.btn.setOnClickListener { viewModel.params?.btnClickListener()?.onClick(0) }
    }
}