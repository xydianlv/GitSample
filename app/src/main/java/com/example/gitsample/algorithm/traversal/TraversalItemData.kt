package com.example.gitsample.algorithm.traversal

/**
 * Created by wyyu on 2024/2/17.
 **/
data class TraversalItemData(
    @TraversalType val traversalType: String,
    val resultList: ArrayList<NodeData> = ArrayList()
) {

    companion object {

        @JvmStatic
        fun buildTraversalList(): ArrayList<TraversalItemData> {
            val list = ArrayList<TraversalItemData>()
            list.add(TraversalItemData(TraversalType.PRE))
            list.add(TraversalItemData(TraversalType.MID))
            list.add(TraversalItemData(TraversalType.NEXT))
            list.add(TraversalItemData(TraversalType.ROW))
            return list
        }
    }
}