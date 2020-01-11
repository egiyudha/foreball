package com.yudha.foreball.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yudha.foreball.R
import com.yudha.foreball.model.League

class LeagueAdapter(private val clickListener: (League) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    private val leagues: MutableList<League> = ArrayList()

    fun swapData(newData: List<League>) {
        leagues.clear()
        leagues.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.simple_list_item, parent, false)
        return LeagueViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]
        holder.bind(league)
    }

    fun deleteLeague(position: Int): League {
        val league = leagues[position]
        leagues.removeAt(position)
        notifyItemRemoved(position)
        return league
    }

    class LeagueViewHolder(itemView: View, private val clickListener: (League) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.textView)

        fun bind(league: League) {
            textView.isClickable = true
            textView.text = league.leagueName
            textView.setOnClickListener { clickListener(league) }
        }
    }
}