package com.yudha.foreball.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yudha.foreball.data.Repository
import com.yudha.foreball.data.Settings
import com.yudha.foreball.model.League
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LeagueViewModel @Inject constructor(
    private val repository: Repository,
    private val settings: Settings
) : BaseViewModel() {
    var leagues: MutableLiveData<List<League>> = MutableLiveData()

    init {
        val disposable = repository.queryAllLeagues()
            .subscribeOn(Schedulers.io())
            .subscribe { leagues.postValue(it) }
        addDisposable(disposable)
    }

    fun onLeagueSelected(league: League) {
        settings.activeLeagueId = league.id
    }

    fun deleteLeague(league: League) {
        settings.activeLeagueId = null
        repository.deleteLeague(league)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}