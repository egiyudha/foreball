package com.yudha.foreball.ui

import androidx.recyclerview.widget.RecyclerView

fun <VH : RecyclerView.ViewHolder> RecyclerView.setup(recyclerViewAdapter: RecyclerView.Adapter<VH>) {
    this.let {
        setHasFixedSize(true)
        adapter = recyclerViewAdapter
    }
}