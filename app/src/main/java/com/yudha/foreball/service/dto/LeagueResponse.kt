package com.yudha.foreball.service.dto

import com.yudha.foreball.model.League

data class LeagueResponse(val list: List<Leagues>)
/*
*
* Problem here
* */
data class Leagues(val league: String) {
    fun toLeague(): League {
        return League(0, league)
    }
}