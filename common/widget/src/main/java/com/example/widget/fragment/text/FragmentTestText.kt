package com.example.widget.fragment.text

import android.view.LayoutInflater
import com.example.widget.databinding.FragmentTestTextBinding
import com.example.widget.fragment.BaseFragment

class FragmentTestText : BaseFragment<FragmentTestTextBinding>() {

    companion object {

        @JvmField
        var textValue: String? = null

        @JvmStatic
        fun fragment(textValue: CharSequence): FragmentTestText {
            FragmentTestText.textValue = textValue.toString()
            return FragmentTestText()
        }
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentTestTextBinding {
        return FragmentTestTextBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()
        binding.text.text = textValue
    }
}