package com.example.gitsample.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun run(callback: (info: String) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            callback.invoke("ViewModel Test")
        }
    }
}