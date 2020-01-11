package com.yudha.foreball.data

import androidx.room.*
import com.yudha.foreball.model.Match
import io.reactivex.Flowable

@Dao
interface MatchDao {

    @Query("SELECT * FROM `match` WHERE league_id = :leagueId")
    fun queryForLeague(leagueId: Int): Flowable<List<Match>>

    @Query("DELETE FROM `match` WHERE league_id = :leagueId ")
    fun deleteForLeague(leagueId: Int)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertMatches(matches: List<Match>)


    @Transaction
    fun updateMatchList(leagueId: Int, matches: List<Match>) {
        deleteForLeague(leagueId)
        insertMatches(matches)
    }
}