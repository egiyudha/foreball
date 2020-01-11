package com.yudha.foreball.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yudha.foreball.data.Repository
import com.yudha.foreball.data.Settings
import com.yudha.foreball.model.League
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val settings: Settings
) : BaseViewModel() {
    val searchResult: MutableLiveData<List<League>> = MutableLiveData()
    val errors: MutableLiveData<Throwable> = MutableLiveData()

    fun search(searchKey: CharSequence) {
        val disposable = repository.searchLeague()
            .subscribeOn(Schedulers.io())
            .subscribe({ searchResult.postValue(it) }, { errors.postValue(it) })
        addDisposable(disposable)
    }

    fun onLeagueSelected(league: League) {
        settings.activeLeagueId = league.id

        repository.insertLeague(league)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}