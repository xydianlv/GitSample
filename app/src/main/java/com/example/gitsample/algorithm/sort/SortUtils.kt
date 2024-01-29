package com.example.gitsample.algorithm.sort


/**
 * Created by wyyu on 2024/1/28.
 **/
object SortUtils {

    @JvmStatic
    fun sortArray(
        @SortType sortType: String,
        array: Array<Int>,
        callback: (time: Long, result: Array<Int>) -> Unit
    ) {
        val startTime = System.currentTimeMillis()
        val result = when (sortType) {
            SortType.SORT_POP -> {
                sortPop(array)
                array
            }

            SortType.SORT_INSERT -> {
                sortInsert(array)
                array
            }

            SortType.SORT_SELECT -> {
                sortSelect(array)
                array
            }

            SortType.SORT_MERGE -> {
                sortMerge(array)
            }

            SortType.SORT_QUICK -> {
                sortQuick(array, 0, array.lastIndex)
                array
            }

            SortType.SORT_HEAP -> {
                sortHeap(array, array.lastIndex)
                array
            }

            SortType.SORT_SUM -> {
                sortSum(array)
                array
            }

            SortType.SORT_BASE -> {
                sortBase(array, 1)
                array
            }

            else -> {
                array
            }
        }
        callback.invoke(System.currentTimeMillis() - startTime, result)
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
    private fun sortInsert(array: Array<Int>) {
        array.forEachIndexed { index, _ ->
            for (position in index downTo 1) {
                if (array[position] < array[position - 1]) {
                    swipeArrayIndex(array, position, position - 1)
                } else {
                    break
                }
            }
        }
    }

    @JvmStatic
    // 交换数组中两个 index 位置的数据
    private fun swipeArrayIndex(array: Array<Int>, indexPre: Int, indexNext: Int) {
        array[indexPre] = array[indexPre] + array[indexNext]
        array[indexNext] = array[indexPre] - array[indexNext]
        array[indexPre] = array[indexPre] - array[indexNext]
    }

    @JvmStatic
    // 选择排序：遍历数组，每遍历到一个位置，从该位置往后找到最小的数，交换该位置与最小的数的位置
    // 时间复杂度：平均 -- O(n2)、最大 -- O(n2)、最小 -- O(n2)，每次寻找最小的数，都要遍历数组后面的所有的数
    // 空间复杂度：O(1)
    // 稳定性：稳定
    private fun sortSelect(array: Array<Int>) {
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
    }

    @JvmStatic
    // 归并排序：将数组先二分为最小的组，然后再根据大小顺序合并相邻的组，每次合并的都是已经排好序的组
    // 时间复杂度：平均 -- O(n*log2n)、最大 -- O(n*log2n)、最小 -- O(n*log2n)
    // 空间复杂度：O(n)，在数组合并时，需要启用一个备用的数组空间
    // 稳定性：稳定
    private fun sortMerge(array: Array<Int>): Array<Int> {
        if (array.size <= 1) {
            return array
        }
        val arrayLeft = array.copyOfRange(0, array.size / 2)
        val arrayRight = array.copyOfRange(array.size / 2, array.size)

        return sortMerge(sortMerge(arrayLeft), sortMerge(arrayRight))
    }

    @JvmStatic
    private fun sortMerge(arrayLeft: Array<Int>, arrayRight: Array<Int>): Array<Int> {
        val result = ArrayList<Int>()

        var indexLeft = 0
        var indexRight = 0

        while (indexLeft < arrayLeft.size && indexRight < arrayRight.size) {
            if (arrayLeft[indexLeft] < arrayRight[indexRight]) {
                result.add(arrayLeft[indexLeft])
                indexLeft++
            } else {
                result.add(arrayRight[indexRight])
                indexRight++
            }
        }
        if (indexLeft < arrayLeft.size) {
            for (index in indexLeft until arrayLeft.size) {
                result.add(arrayLeft[index])
            }
        }
        if (indexRight < arrayRight.size) {
            for (index in indexRight until arrayRight.size) {
                result.add(arrayRight[index])
            }
        }

        return result.toTypedArray()
    }

    @JvmStatic
    // 快速排序：在数组中先选中一个基数，然后遍历该基数之外的数，比它小的放在前面，比它大的放在后面，然后把基数放在中间目标位，再对前后数组重复上述操作
    // 时间复杂度：平均 -- O(n*log2n)、最大 -- O(n2)、最小 -- O(n*log2n)
    // 空间复杂度：O(n)，递归的过程需要 O(n) 大小的空间来存储划分位置
    // 稳定性：不稳定
    private fun sortQuick(array: Array<Int>, indexStart: Int, indexEnd: Int) {
        if (indexStart >= indexEnd) {
            return
        }
        var indexLeft = indexStart + 1
        var indexRight = indexEnd

        while (indexLeft < indexRight) {
            if (array[indexLeft] < array[indexStart]) {
                indexLeft++
            } else if (array[indexRight] > array[indexStart]) {
                indexRight--
            } else {
                swipeArrayIndex(array, indexLeft, indexRight)
                indexLeft++
                indexRight--
            }
        }
        val indexMid = if (array[indexLeft] < array[indexStart]) {
            indexLeft
        } else {
            indexLeft - 1
        }
        if (indexStart != indexMid) {
            swipeArrayIndex(array, indexStart, indexMid)
        }

        sortQuick(array, indexStart, indexMid - 1)
        sortQuick(array, indexMid + 1, indexEnd)
    }

    @JvmStatic
    // 堆排序：将数组维护成一个最大堆，没完成一次维护，交换第一个位置的最大数到堆末尾，再缩减堆的元素个数，以此类推
    // 时间复杂度：平均 -- O(n*log2n)、最大 -- O(n*log2n)、最小 -- O(n*log2n)
    // 空间复杂度：O(1)
    // 稳定性：不稳定
    private fun sortHeap(array: Array<Int>, lastIndex: Int) {
        if (lastIndex <= 0) {
            return
        }
        for (index in lastIndex downTo 0) {
            if (array[index] > array[index / 2]) {
                swipeArrayIndex(array, index, index / 2)
            }
        }
        swipeArrayIndex(array, 0, lastIndex)
        sortHeap(array, lastIndex - 1)
    }

    @JvmStatic
    // 计数排序：先找出数组中最大和最小的数，再创立一个最大差值的数组，再将每个数字出现的次数统计到自身所在的位置，最后从小到大取出所有数
    // 时间复杂度：平均 -- O(n+k)、最大 -- O(n+ k)、最小 -- O(n+k)，n 个 0 到 k 之间的数
    // 空间复杂度：O(n+k)
    // 稳定性：不稳定
    private fun sortSum(array: Array<Int>) {
        var minValue = array[0]
        var maxValue = array[0]

        array.forEach {
            if (it < minValue) {
                minValue = it
            }
            if (it > maxValue) {
                maxValue = it
            }
        }

        val countArray = Array(maxValue - minValue + 1) { 0 }
        array.forEach {
            countArray[it - minValue]++
        }

        var arrayIndex = 0
        countArray.forEachIndexed { index, value ->
            for (position in 0 until value) {
                array[arrayIndex] = minValue + index
                arrayIndex++
            }

        }
    }

    @JvmStatic
    // 基数排序：从个位开始，将每个数字放到自己除余的列表中，再从0— 9 收录，然后从十位开始，重复上述操作
    // 时间复杂度：平均 -- O(n*k)、最大 -- O(n*k)、最小 -- O(n*k)，n 个 0 到 k 之间的数
    // 空间复杂度：O(n+k)，一般来说 n 要远远大于 k，基本可认为是 O(n)
    // 稳定性：不稳定
    private fun sortBase(array: Array<Int>, baseData: Int) {
        val baseArray: Array<ArrayList<Int>> = Array(10) { ArrayList() }
        val basePre = baseData * 10

        var moreBase = false
        array.forEach {
            baseArray[it % basePre / baseData].add(it)
            if (it / basePre > 0) {
                moreBase = true
            }
        }

        var arrayIndex = 0
        baseArray.forEach { list ->
            list.forEach { data ->
                array[arrayIndex] = data
                arrayIndex++
            }
        }

        if (moreBase) {
            sortBase(array, basePre)
        }
    }
}