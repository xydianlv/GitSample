package com.example.gitsample.function.applayout.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class ListAdapter : Adapter<ListHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListHolder {
        return ListHolder(LayoutInflater.from(p0.context).inflate(ListHolder.layout(), p0, false))
    }

    override fun onBindViewHolder(p0: ListHolder, p1: Int) {
    }

    override fun getItemCount(): Int {
        return 20
    }
}