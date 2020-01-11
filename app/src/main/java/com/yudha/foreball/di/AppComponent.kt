package com.yudha.foreball.di

import com.yudha.foreball.data.Repository
import com.yudha.foreball.service.MatchApi
import com.yudha.foreball.ui.LeagueListActivity
import com.yudha.foreball.ui.MatchActivity
import com.yudha.foreball.ui.SearchActivity
import com.yudha.foreball.viewmodel.SearchViewModel
import com.yudha.foreball.viewmodel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun provideMatchRestApi(): MatchApi

    fun provideRepository(): Repository

    fun provideSearchViewModelFactory(): ViewModelFactory

    fun inject(searchViewModel: SearchViewModel)

    fun inject(searchActivity: SearchActivity)

    fun inject(leagueListActivity: LeagueListActivity)

    fun inject(matchListActivity: MatchActivity)
}