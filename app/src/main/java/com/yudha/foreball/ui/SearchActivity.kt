package com.yudha.foreball.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yudha.foreball.R
import com.yudha.foreball.di.InjectorFactory
import com.yudha.foreball.model.League
import com.yudha.foreball.ui.adapter.LeagueAdapter
import com.yudha.foreball.viewmodel.SearchViewModel
import com.yudha.foreball.viewmodel.ViewModelFactory
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    companion object {
        private val LOG_TAG = this::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SearchViewModel
    private val searchResultsAdapter = LeagueAdapter(this::onClickLeague)
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectorFactory.newAppInjector(application).inject(this)
        initViewModel()
        initUI()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        viewModel.searchResult.observe(this, Observer {
            searchResultsAdapter.swapData(it ?: Arrays.asList())
        })
        viewModel.errors.observe(this, Observer { onError(it) })
    }

    private fun initUI() {
        setContentView(R.layout.activity_search)
        recyclerView.setup(searchResultsAdapter)

        disposable = TextChangeObserver()
            .observeTextChanges(searchEditText)
            .subscribe { viewModel.search(it) }
    }

    private fun onError(error: Throwable?) {
        Log.e(LOG_TAG, "Error Search City", error)
    }

    private fun onClickLeague(league: League) {
        viewModel.onLeagueSelected(league)

        val intent = Intent(this, MatchActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}