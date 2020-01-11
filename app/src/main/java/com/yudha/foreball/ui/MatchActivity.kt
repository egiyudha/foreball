package com.yudha.foreball.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yudha.foreball.R
import com.yudha.foreball.databinding.ActivityMatchBinding
import com.yudha.foreball.di.InjectorFactory
import com.yudha.foreball.ui.adapter.MatchAdapter
import com.yudha.foreball.viewmodel.MatchViewModel
import com.yudha.foreball.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_match.*
import java.util.*
import javax.inject.Inject

class MatchActivity : AppCompatActivity() {
    companion object {
        private val LOG_TAG = this::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val matchAdapter = MatchAdapter()
    private lateinit var viewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectorFactory.newAppInjector(application).inject(this)
        initViewModel()
        initUI()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)
        viewModel.match.observe(this, Observer {
            matchAdapter.swapData(it ?: Arrays.asList())
        })
        viewModel.errors.observe(this, Observer { onError(it) })
    }

    private fun initUI() {
        val binding: ActivityMatchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_match)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        recyclerView.setup(matchAdapter)
    }

    private fun onError(error: Throwable?) {
        Log.e(LOG_TAG, "Error fetch", error)
        Toast.makeText(this, "Error Fetching...", Toast.LENGTH_LONG).show()
    }

    fun onSearchClick(view: View) {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    fun onLeagueListClick(view: View) {
        startActivity(Intent(this, LeagueListActivity::class.java))
    }
}