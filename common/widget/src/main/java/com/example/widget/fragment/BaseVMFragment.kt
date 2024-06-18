package com.example.widget.fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseVMFragment<vb : ViewBinding, vm : ViewModel> : BaseFragment<vb>() {

    protected lateinit var viewModel: vm

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    abstract fun getViewModelClass(): Class<vm>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    open fun initData() {
        // 初始化 Data
    }
}