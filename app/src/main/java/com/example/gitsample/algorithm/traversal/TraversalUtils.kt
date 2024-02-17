package com.example.gitsample.algorithm.traversal

/**
 * Created by wyyu on 2024/1/29.
 **/
object TraversalUtils {

    @JvmStatic
    fun buildRootNode(): NodeData {
        val nodeH = NodeData("H")
        val nodeI = NodeData("I")
        val nodeJ = NodeData("J")
        val nodeK = NodeData("K")

        val nodeG = NodeData("G", nodeJ, nodeK)
        val nodeF = NodeData("F", nodeH, nodeI)
        val nodeE = NodeData("E")
        val nodeD = NodeData("D", null, nodeG)
        val nodeC = NodeData("C", nodeE, nodeF)
        val nodeB = NodeData("B", nodeD)

        return NodeData("A", nodeB, nodeC)
    }

    @JvmStatic
    fun traversal(@TraversalType type: String, rootNode: NodeData): ArrayList<NodeData> {
        val list = ArrayList<NodeData>()
        when (type) {
            TraversalType.PRE -> {
                traversalPre(rootNode, list)
            }

            TraversalType.MID -> {
                traversalMid(rootNode, list)
            }

            TraversalType.NEXT -> {
                traversalNext(rootNode, list)
            }

            TraversalType.ROW -> {
                list.add(rootNode)
                traversalRow(list, 0)
            }

            else -> {
                traversalPre(rootNode, list)
            }
        }
        return list
    }

    @JvmStatic
    // 前序遍历：按照根节点、左节点、右节点的优先顺序遍历二叉树
    private fun traversalPre(rootNode: NodeData?, list: ArrayList<NodeData>) {
        rootNode?.let {
            list.add(it)
            traversalPre(it.childLeft, list)
            traversalPre(it.childRight, list)
        }
    }

    @JvmStatic
    // 中序遍历：按照左节点、根节点、右节点的优先顺序遍历二叉树
    private fun traversalMid(rootNode: NodeData?, list: ArrayList<NodeData>) {
        rootNode?.let {
            traversalMid(it.childLeft, list)
            list.add(it)
            traversalMid(it.childRight, list)
        }
    }

    @JvmStatic
    // 后序遍历：按照左节点、右节点、根节点的优先顺序遍历二叉树
    private fun traversalNext(rootNode: NodeData?, list: ArrayList<NodeData>) {
        rootNode?.let {
            traversalNext(it.childLeft, list)
            traversalNext(it.childRight, list)
            list.add(it)
        }
    }

    @JvmStatic
    // 层序遍历：按照层级遍历二叉树
    private fun traversalRow(list: ArrayList<NodeData>, onIndex: Int) {
        if (onIndex >= list.size) {
            return
        }
        var nodeCount = 0
        for (index in onIndex until list.size) {
            val node = list[index]
            if (node.childLeft != null) {
                list.add(node.childLeft)
            }
            if (node.childRight != null) {
                list.add(node.childRight)
            }
            nodeCount++
        }
        traversalRow(list, onIndex + nodeCount)
    }
}