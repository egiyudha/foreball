package com.yudha.foreball.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yudha.foreball.model.Match

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private val match: MutableList<Match> = ArrayList()

    fun swapData(newData: List<Match>) {
        match.clear()
        match.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(match[position])
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val matchTextView = itemView.findViewById<TextView>(android.R.id.text1)
        private val dateTimeTextView = itemView.findViewById<TextView>(android.R.id.text2)

        fun bind(match: Match) {
            matchTextView.text = match.eventName
            dateTimeTextView.text = match.playTime
        }
    }
}