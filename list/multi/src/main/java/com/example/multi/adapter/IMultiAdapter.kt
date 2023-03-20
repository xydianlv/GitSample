package com.example.multi.adapter

import com.example.multi.cell.IHolderCell

interface IMultiAdapter<K, D> {

    fun getItemList(): ArrayList<D>

    fun updateItem(position: Int, updateType: Int, vararg params: Any?)

    fun registerItem(key: K, holderCell: IHolderCell)

    fun registerNullItem(holderCell: IHolderCell)

    fun bindParams(vararg params: Any)
}