package com.example.widget.list

import androidx.recyclerview.widget.RecyclerView
import com.example.multi.adapter.MultiAdapter
import com.example.multi.cell.ICellManager

class CommonListAdapter <K, D>(
    cellManager: ICellManager<K, D>,
    recyclerView: RecyclerView
) :
    MultiAdapter<K, D>(cellManager, recyclerView) {

    fun initList(list: List<D>) {
        if (list.isEmpty()) {
            return
        }
        val itemList = getItemList()
        itemList.clear()
        itemList.addAll(list)

        notifyItemRangeInserted(0, list.size)
    }

    fun updateItem(index: Int, data: D) {
        val itemList = getItemList()
        if (itemList.isEmpty() || index > itemList.lastIndex) {
            return
        }
        itemList.removeAt(index)
        itemList.add(index, data)

        notifyItemChanged(index)
    }
}