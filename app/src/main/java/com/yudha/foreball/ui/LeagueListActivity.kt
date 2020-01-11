package com.yudha.foreball.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.yudha.foreball.R
import com.yudha.foreball.di.InjectorFactory
import com.yudha.foreball.model.League
import com.yudha.foreball.ui.adapter.LeagueAdapter
import com.yudha.foreball.ui.adapter.support.SwipeDelete
import com.yudha.foreball.viewmodel.LeagueViewModel
import com.yudha.foreball.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import javax.inject.Inject

class LeagueListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LeagueViewModel
    private val adapter = LeagueAdapter(this::onLeagueClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectorFactory.newAppInjector(application).inject(this)
        initViewModel()
        initUI()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LeagueViewModel::class.java)
        viewModel.leagues.observe(this, Observer { adapter.swapData(it ?: Arrays.asList()) })
    }

    private fun initUI() {
        setContentView(R.layout.activity_league_list)
        recyclerView.setup(adapter)
    }

//    private fun enableSwipe() {
//        val itemTouchHelper = ItemTouchHelper(object : SwipeDelete(this) {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val deletedLeague = adapter.deleteLeague(viewHolder.adapterPosition)
//                viewModel.deleteLeague(deletedLeague)
//            }
//        })
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }

    private fun onLeagueClick(league: League) {
        viewModel.onLeagueSelected(league)
        val intent = Intent(this, MatchActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}