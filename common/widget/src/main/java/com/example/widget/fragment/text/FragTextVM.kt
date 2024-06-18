package com.example.widget.fragment.text

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.base.ComConstants

class FragTextVM : ViewModel() {

    var params: FragTextParams? = null

    fun initData(args: Bundle?) {
        val value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            args?.getSerializable(ComConstants.KEY_ENTITY_00, FragTextParams::class.java)
        } else {
            null
        }
        if (value is FragTextParams) {
            params = value
        }
    }
}