package com.example.widget.fragment.text

import android.os.Bundle
import com.example.base.ComConstants
import com.example.base.listener.OnComClickListener
import java.io.Serializable

class FragTextParams private constructor() : Serializable {

    companion object {

        @JvmStatic
        fun obj(): FragTextParams {
            return FragTextParams()
        }
    }

    private var text: String? = null

    private var btnText: String? = null

    private var listener: OnComClickListener? = null

    fun textValue(text: String): FragTextParams {
        this.text = text
        return this
    }

    fun textValue(): String? {
        return text
    }

    fun btnTextValue(text: String, listener: OnComClickListener): FragTextParams {
        this.btnText = text
        this.listener = listener
        return this
    }

    fun btnTextValue(): String? {
        return btnText
    }

    fun btnClickListener(): OnComClickListener? {
        return listener
    }

    fun build(): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(ComConstants.KEY_ENTITY_00, this)
        return bundle
    }
}