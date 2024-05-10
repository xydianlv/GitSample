package com.example.widget.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListHolder {
        return ListHolder(
            LayoutInflater.from(p0.context).inflate(ListHolder.layout(), p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(p0: ListHolder, p1: Int) {
    }
}