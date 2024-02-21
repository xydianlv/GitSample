package com.example.gitsample.algorithm.link

/**
 * Created by wyyu on 2024/2/21.
 **/
data class NodeData(
    var nodeValue: String,
    var nodeNext: NodeData? = null,
    var nodePre: NodeData? = null
)