package com.yudha.foreball.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yudha.foreball.data.Repository
import com.yudha.foreball.data.Settings
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: Repository,
    private val settings: Settings
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(
                repository,
                settings
            ) as T
            modelClass.isAssignableFrom(LeagueViewModel::class.java) -> LeagueViewModel(
                repository,
                settings
            ) as T
            modelClass.isAssignableFrom(MatchViewModel::class.java) -> MatchViewModel(
                repository,
                settings
            ) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}