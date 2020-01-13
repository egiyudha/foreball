package com.yudha.foreball.service.dto

import com.yudha.foreball.model.Match

data class MatchRes(
    val city: String,
    val date: String,
    val event_name: String,
    val event_outcome_type: String,
    val eventkey: Int,
    val home_team: Homte,
    val match_id: String,
    val season: Int,
    val stadium: String,
    val start_time: String,
    val tournament_name: String,
    val tournament_type: String,
    val visitant_team: Viste
){
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

data class Homte(
    val assitant: List<Any>,
    val image: String,
    val name: Name
)

data class Name(
    val abbrev: String,
    val first: String,
    val full: String
)

data class Viste(
    val assitant: List<Any>,
    val image: String,
    val name: NameX
)

data class NameX(
    val abbrev: String,
    val first: String,
    val full: String
)