package com.example.base.listener

class ZCallback {

    interface ZCallbackF {

        fun callback()
    }

    interface ZCallbackF0<T> {

        fun callback(value: T)
    }

    interface ZCallbackFB0<T> {

        fun callback(): T
    }
}