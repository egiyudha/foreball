package com.yudha.foreball.data

import com.yudha.foreball.model.League
import com.yudha.foreball.model.Match
import com.yudha.foreball.service.MatchApi
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class Repository @Inject constructor(
    private val api: MatchApi,
    private val leagueDao: LeagueDao,
    private val matchDao: MatchDao
) {

    open fun searchLeague(): Observable<List<League>> {
        return api.getLeague()
            .map { it.list.map { league -> league.toLeague() } }
    }

    fun queryAllLeagues(): Flowable<List<League>> = leagueDao.queryAllLeague()

    fun queryLeagueCount(): Flowable<Int> = leagueDao.queryLeagueCount()

    fun queryLeague(id: Int): Flowable<League> = leagueDao.queryLeague(id)

    fun insertLeague(league: League): Single<Int> {
        return Single.create { leagueDao.insertLeague(league) }
    }

    fun deleteLeague(league: League): Single<Int> {
        return Single.create { leagueDao.deleteLeague(league) }
    }

    fun queryMatch(leagueId: Int): Observable<List<Match>> {
        val apiMatch = null
        val dbMatch = matchDao.queryForLeague(leagueId).toObservable()
        return Observable.mergeDelayError(apiMatch, dbMatch)
    }

    private fun fetchMatch(leagueId: Int): Observable<List<Match>> {
        return api.getMatches()
            .map { it.list.map { matches -> matches.toMatch(leagueId) } }
            .doOnNext { matchDao.updateMatchList(leagueId, it) }
    }
}