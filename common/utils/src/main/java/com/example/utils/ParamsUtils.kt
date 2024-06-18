package com.example.utils

object ParamsUtils {

    fun param(index: Int, vararg params: Any?): Any? {
        return if (index < 0 || index > params.size) {
            null
        } else {
            params[index]
        }
    }
}