package com.yudha.foreball.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "match", foreignKeys = [
        ForeignKey(
            entity = League::class,
            parentColumns = ["id"],
            childColumns = ["league_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Match(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "league_id", index = true)
    val leagueId: Int,

    @ColumnInfo(name = "event_name")
    val eventName: String,

    @ColumnInfo(name = "play_time")
    val playTime: String,

    @ColumnInfo(name = "tournament_name")
    val tournamentName: String,

    @ColumnInfo(name = "away_team_name")
    val awayTeamName: String,

    @ColumnInfo(name = "home_team_name")
    val homeTeamName: String,

    @ColumnInfo(name = "away_team_image")
    val awayTeamImage: String,

    @ColumnInfo(name = "home_team_image")
    val homeTeamImage: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "season")
    val season: Int,

    @ColumnInfo(name = "stadium")
    val stadium: String
)
/*
{
    companion object {
        val DATE_FORMAT = SimpleDateFormat("dd MMMM HH:mm", Locale.US)
    }

    fun dateTime(): String {
        return DATE_FORMAT.format(Date(playTime))
    }
}*/
