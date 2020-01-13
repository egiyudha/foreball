package com.yudha.foreball.service

import com.yudha.foreball.service.dto.LeagueResponse
import com.yudha.foreball.service.dto.MatchRes
import com.yudha.foreball.service.dto.MatchResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MatchApi {

    @GET("s1/tournaments")
    fun getLeague(): Observable<List<LeagueResponse>>

    @GET("s1/calendar/2017-01-01/2017-01-01?tournament_name=English%20Premier%20League")
    fun getMatches(): Observable<List<MatchRes>>
}