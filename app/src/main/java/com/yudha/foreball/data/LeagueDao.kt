package com.yudha.foreball.data

import androidx.room.*
import com.yudha.foreball.model.League
import io.reactivex.Flowable

@Dao
interface LeagueDao {
    @Query("SELECT * FROM league")
    fun queryAllLeague(): Flowable<List<League>>

    @Query("SELECT COUNT(id) FROM league")
    fun queryLeagueCount(): Flowable<Int>

    @Query("SELECT * FROM league WHERE id = :leagueId")
    fun queryLeague(leagueId: Int): Flowable<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(league: League): Long

    @Delete
    fun deleteLeague(league: League): Int
}