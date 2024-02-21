package com.example.gitsample.algorithm.link

/**
 * Created by wyyu on 2024/2/21.
 **/
object LinkUtils {

    @JvmStatic
    fun buildLink(): NodeData {
        val nodeDataD = NodeData("D")
        val nodeDataC = NodeData("C", nodeDataD)
        val nodeDataB = NodeData("B", nodeDataC)
        return NodeData("A", nodeDataB)
    }

    @JvmStatic
    fun buildResultStr(rootNode: NodeData): String {
        val stringBuilder = StringBuilder()
        var funNode: NodeData? = rootNode
        while (funNode != null) {
            stringBuilder.append(funNode.nodeValue)
            if (funNode.nodeNext != null) {
                stringBuilder.append(" -> ")
            }
            funNode = funNode.nodeNext
        }
        return stringBuilder.toString()
    }

    @JvmStatic
    fun changeList(rootNode: NodeData): NodeData {
        if (rootNode.nodeNext == null) {
            return rootNode
        }
        val funNode = changeList(rootNode.nodeNext!!)
        rootNode.nodeNext!!.nodeNext = rootNode
        rootNode.nodeNext = null
        return funNode
    }

}