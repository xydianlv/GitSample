package com.example.gitsample.algorithm.sort

import androidx.annotation.IntDef
import androidx.annotation.StringDef

/**
 * Created by wyyu on 2024/1/29.
 **/
@StringDef(
    SortType.SORT_POP,
    SortType.SORT_INSERT,
    SortType.SORT_SELECT,
    SortType.SORT_MERGE,
    SortType.SORT_QUICK,
    SortType.SORT_HEAP,
    SortType.SORT_SUM,
    SortType.SORT_BASE
)
annotation class SortType {

    companion object {

        const val SORT_POP = "PopSort"

        const val SORT_INSERT = "InsertSort"

        const val SORT_SELECT = "SelectSort"

        const val SORT_MERGE = "MergeSort"

        const val SORT_QUICK = "QuickSort"

        const val SORT_HEAP = "HeapSort"

        const val SORT_SUM = "SumSort"

        const val SORT_BASE = "BaseSort"
    }
}