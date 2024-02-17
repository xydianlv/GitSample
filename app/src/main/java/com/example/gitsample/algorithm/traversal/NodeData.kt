package com.example.gitsample.algorithm.traversal

/**
 * Created by wyyu on 2024/1/29.
 **/

data class NodeData(
    val nodeValue: String,
    val childLeft: NodeData? = null,
    val childRight: NodeData? = null
)