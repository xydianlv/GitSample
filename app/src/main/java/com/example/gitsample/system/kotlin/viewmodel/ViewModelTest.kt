package com.example.gitsample.system.kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTest : ViewModel() {

    fun run(preInfo: String, callback: (info: String) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            callback.invoke(preInfo)
        }
    }
}