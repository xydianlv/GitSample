package com.example.widget.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseVMActivity<vm : ViewModel, vb : ViewBinding> : BaseActivity<vb>() {

    protected lateinit var viewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getVMClass()]
    }

    abstract fun getVMClass(): Class<vm>
}