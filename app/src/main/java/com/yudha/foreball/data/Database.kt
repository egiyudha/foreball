package com.yudha.foreball.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yudha.foreball.model.League
import com.yudha.foreball.model.Match

@Database(entities = [League::class, Match::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

    abstract fun matchDao(): MatchDao
}