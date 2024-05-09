package com.example.gitsample.widget.fragment.frag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class ListAdapter : Adapter<ListItemHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListItemHolder {
        return ListItemHolder(
            LayoutInflater.from(p0.context).inflate(ListItemHolder.layout(), p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(p0: ListItemHolder, p1: Int) {
        TODO("Not yet implemented")
    }
}