package com.example.gitsample.algorithm.traversal

import androidx.annotation.StringDef

/**
 * Created by wyyu on 2024/2/17.
 **/
@StringDef(
    TraversalType.PRE,
    TraversalType.MID,
    TraversalType.NEXT,
    TraversalType.ROW
)
annotation class TraversalType {

    companion object {

        const val PRE = "TraversalPre"

        const val MID = "TraversalMid"

        const val NEXT = "TraversalNext"

        const val ROW = "TraversalRow"
    }
}