package com.example.gitsample.algorithm.sort

/**
 * Created by wyyu on 2024/1/29.
 **/
data class SortItemData(
    @SortType val sortType: String,
    val resultList: ArrayList<Int> = ArrayList(),
    var time: Long = 0
) {

    companion object {

        @JvmStatic
        fun buildSortList(): ArrayList<SortItemData> {
            val list = ArrayList<SortItemData>()
            list.add(SortItemData(SortType.SORT_POP))
            list.add(SortItemData(SortType.SORT_INSERT))
            list.add(SortItemData(SortType.SORT_SELECT))
            list.add(SortItemData(SortType.SORT_MERGE))
            list.add(SortItemData(SortType.SORT_QUICK))
            list.add(SortItemData(SortType.SORT_HEAP))
            list.add(SortItemData(SortType.SORT_SUM))
            list.add(SortItemData(SortType.SORT_BASE))
            return list
        }
    }
}