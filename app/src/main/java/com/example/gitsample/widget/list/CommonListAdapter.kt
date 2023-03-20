package com.example.gitsample.widget.list

import androidx.recyclerview.widget.RecyclerView
import com.example.multi.adapter.MultiAdapter
import com.example.multi.cell.ICellManager

class CommonListAdapter<K, D>(
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
}