package com.example.gitsample.function.lock.finger

import com.example.resource.R

data class NodeData(
    val index: Int,
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val x: Float,
    val y: Float,
    var resId: Int = R.mipmap.img_lock_normal
)