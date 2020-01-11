package com.yudha.foreball.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.yudha.foreball.data.Repository
import com.yudha.foreball.data.Settings
import com.yudha.foreball.model.League
import com.yudha.foreball.model.Match
import io.reactivex.schedulers.Schedulers

class MatchViewModel(repository: Repository, settings: Settings) : BaseViewModel() {
    val hasSavedLeagues: ObservableBoolean = ObservableBoolean(false)
    val league: MutableLiveData<League> = MutableLiveData()
    val match: MutableLiveData<List<Match>> = MutableLiveData()
    val errors: MutableLiveData<Throwable> = MutableLiveData()

    init {
        var disposable = repository.queryLeagueCount()
            .subscribeOn(Schedulers.io())
            .subscribe { hasSavedLeagues.set(it > 0) }
        addDisposable(disposable)


        val activeLeagueId = settings.activeLeagueId
        activeLeagueId?.let {
            disposable = repository.queryLeague(activeLeagueId)
                .subscribeOn(Schedulers.io())
                .subscribe { l -> league.postValue(l) }
            addDisposable(disposable)

            disposable = repository.queryMatch(activeLeagueId)
                .subscribeOn(Schedulers.io())
                .subscribe({ m -> match.postValue(m) }, { t -> errors.postValue(t) })
            addDisposable(disposable)
        }
    }

}