package com.example.gitsample.algorithm.sort

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by wyyu on 2024/1/28.
 **/
object SortUtils {

    @JvmStatic
    fun sortPop(array: Array<Int>, callback: (time: Long, result: Array<Int>) -> Unit) {
        val startTime = System.currentTimeMillis()
        sortPop(array)
        callback.invoke(System.currentTimeMillis() - startTime, array)
    }


    @JvmStatic
    // 冒泡排序：遍历数组，若存在相邻数据可交换，则交换该对相邻数据，并准备下次遍历，直到没有可交换为止
    // 时间复杂度：平均 -- O(n2)、最大 -- O(n2)、最小 -- O(n)，对于一个顺序数组，一次遍历即可退出
    // 空间复杂度：O(1)
    // 稳定性：稳定
    private fun sortPop(array: Array<Int>) {
        var needPop = false
        array.forEachIndexed { index, value ->
            if (index > 0 && value < array[index - 1]) {
                array[index] = array[index - 1]
                array[index - 1] = value
                needPop = true
            }
        }
        if (needPop) {
            sortPop(array)
        }
    }

    @JvmStatic
    // 插入排序：遍历数组，将遍历到的数据，往前面插入到自己的目标位置，该方法每次完成插入后，前面都是顺序数组
    // 时间复杂度：平均 -- O(n2)、最大 -- O(n2)、最小 -- O(n)，对于一个顺序数组，每个数据本身就在自己的目标位置
    // 空间复杂度：O(1)
    // 稳定性：稳定
    fun sortInsert(array: Array<Int>, callback: (time: Long, result: Array<Int>) -> Unit) {
        val startTime = System.currentTimeMillis()
        array.forEachIndexed { index, _ ->
            for (position in index downTo 1) {
                if (array[position] < array[position - 1]) {
                    array[position] = array[position] + array[position - 1]
                    array[position - 1] = array[position] - array[position - 1]
                    array[position] = array[position] - array[position - 1]
                } else {
                    break
                }
            }
        }
        callback.invoke(System.currentTimeMillis() - startTime, array)
    }

    @JvmStatic
    // 选择排序：遍历数组，每遍历到一个位置，从该位置往后找到最小的数，交换该位置与最小的数的位置
    // 时间复杂度：平均 -- O(n2)、最大 -- O(n2)、最小 -- O(n2)，每次寻找最小的数，都要遍历数组后面的所有的数
    // 空间复杂度：O(1)
    // 稳定性：稳定
    fun sortSelect(array: Array<Int>, callback: (time: Long, result: Array<Int>) -> Unit) {
        val startTime = System.currentTimeMillis()
        array.forEachIndexed { index, value ->
            var minValue = value
            var minIndex = index
            for (position in index + 1 until array.size) {
                if (array[position] < minValue) {
                    minIndex = position
                    minValue = array[position]
                }
            }
            if (minIndex != index) {
                array[minIndex] = value
                array[index] = minValue
            }
        }
        callback.invoke(System.currentTimeMillis() - startTime, array)
    }

    @JvmStatic
    fun sortMerge(array: Array<Int>, callback: (time: Long, result: Array<Int>) -> Unit) {

    }

    @JvmStatic
    fun sortQuick(array: Array<Int>, callback: (time: Long, result: Array<Int>) -> Unit) {

    }
}