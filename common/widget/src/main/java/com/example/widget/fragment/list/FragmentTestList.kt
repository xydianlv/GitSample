package com.example.widget.fragment.list

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.widget.databinding.FragmentTestListBinding
import com.example.widget.fragment.BaseFragment

class FragmentTestList : BaseFragment<FragmentTestListBinding>() {

    companion object {

        @JvmStatic
        fun getFragment(): FragmentTestList {
            return FragmentTestList()
        }
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentTestListBinding {
        return FragmentTestListBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = ListAdapter()
    }
}