package com.yudha.foreball.service.dto

import com.yudha.foreball.model.League

data class LeagueResponse(val league: String){
    fun toLeague(): League {
        return League(0, league)
    }
}