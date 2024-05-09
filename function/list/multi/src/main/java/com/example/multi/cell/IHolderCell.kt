package com.example.multi.cell

import android.view.View
import androidx.annotation.LayoutRes

interface IHolderCell {

    @LayoutRes
    fun getHolderLayout(): Int

    fun onCreateView(itemView: View)

    fun onBindCell(position: Int, itemData: Any?, vararg params: Any?)

    fun onUpdateCell(updateType: Int, vararg params: Any?)
}