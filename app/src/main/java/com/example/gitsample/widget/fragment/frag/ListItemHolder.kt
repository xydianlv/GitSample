package com.example.gitsample.widget.fragment.frag

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gitsample.R

class ListItemHolder(itemView: View) : ViewHolder(itemView) {

    companion object {

        @JvmStatic
        @LayoutRes
        fun layout(): Int {
            return R.layout.layout_list_holder
        }
    }
}