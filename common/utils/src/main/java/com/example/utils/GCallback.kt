package com.example.utils

fun interface GCallback00 {
    fun callback()
}

fun interface GCallback01<T> {

    fun callback(value: T)
}

fun interface GCallbackR01<T> {

    fun callback(): T
}

fun interface GCallback02<T, K> {

    fun callback(valueT: T, valueK: K)
}

interface GCallbackR02<T, K> {

    fun callbackT(): T

    fun callbackK(): K
}