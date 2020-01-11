package com.yudha.foreball.service.dto

import com.yudha.foreball.model.Match

data class MatchResponse(val list: List<Matches>)

data class Matches(
    val city: String,
    val event_name: String,
    val home_team: HomeTeam,
    val season: Int,
    val stadium: String,
    val start_time: String,
    val tournament_name: String,
    val visitant_team: VisitantTeam
) {
    fun toMatch(leagueId: Int): Match {
        return Match(
            0,
            leagueId,
            event_name,
            start_time,
            tournament_name,
            visitant_team.name.full,
            home_team.name.full,
            visitant_team.image,
            home_team.image,
            city,
            season,
            stadium
        )
    }
}

data class HomeTeam(
    val assitant: List<Any>,
    val image: String,
    val name: NameHome
)

data class NameHome(
    val abbrev: String,
    val first: String,
    val full: String
)

data class VisitantTeam(
    val assitant: List<Any>,
    val image: String,
    val name: NameVisitant
)

data class NameVisitant(
    val abbrev: String,
    val first: String,
    val full: String
)