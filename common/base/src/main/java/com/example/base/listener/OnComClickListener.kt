package com.example.base.listener

import java.io.Serializable

interface OnComClickListener : Serializable {

    fun onClick(key: Int, vararg params: Any?)
}