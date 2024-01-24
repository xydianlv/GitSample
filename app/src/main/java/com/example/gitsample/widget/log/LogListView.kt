package com.example.gitsample.widget.log

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multi.cell.ClassCellManager

/**
 * Created by wyyu on 2024/1/24.
 **/

class LogListView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var listAdapter: LogListAdapter<Class<out Any>, Any>? = null

    init {
        listAdapter = LogListAdapter(ClassCellManager(), this)
        listAdapter!!.registerItem(String::class.java, LogListItemHolder())

        layoutManager = LinearLayoutManager(context)
        adapter = listAdapter
    }

    fun insertLog(logInfo: String) {
        listAdapter?.apply {
            insertLog(logInfo)
            smoothScrollToPosition(itemCount - 1)
        }
    }
}