package com.example.gitsample.widget.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multi.cell.ClassCellManager

class CommonListView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var listAdapter: CommonListAdapter<Class<out Any>, Any>? = null
    private val dataList: ArrayList<CommonListItemData> = ArrayList()

    init {
        listAdapter = CommonListAdapter(ClassCellManager(), this)
        listAdapter!!.registerItem(CommonListItemData::class.java, CommonListItemHolder())

        layoutManager = LinearLayoutManager(context)
        adapter = listAdapter
    }

    fun addItem(itemData: CommonListItemData): CommonListView {
        dataList.add(itemData)
        return this
    }

    fun initList(list: ArrayList<CommonListItemData>): CommonListView {
        dataList.addAll(list)
        return this
    }

    fun refreshList() {
        listAdapter?.initList(dataList)
    }
}

