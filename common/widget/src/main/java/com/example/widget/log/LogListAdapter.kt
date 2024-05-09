package com.example.widget.log

import androidx.recyclerview.widget.RecyclerView
import com.example.multi.adapter.MultiAdapter
import com.example.multi.cell.ICellManager

class LogListAdapter<K, D>(
    cellManager: ICellManager<K, D>,
    recyclerView: RecyclerView
) : MultiAdapter<K, D>(cellManager, recyclerView) {

    fun insertLog(logInfo: D) {
        val itemList = getItemList()
        itemList.add(logInfo)

        notifyItemInserted(itemList.size - 1)
    }
}