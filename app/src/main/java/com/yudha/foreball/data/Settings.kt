package com.yudha.foreball.data

import android.content.SharedPreferences

class Settings(private val preferences: SharedPreferences) {
    companion object {
        private const val KEY_LEAGUE_ID = "league_id"
    }

    var activeLeagueId: Int?
        get() = if (preferences.contains(KEY_LEAGUE_ID)) preferences.getInt(
            KEY_LEAGUE_ID,
            0
        ) else null
        set(value) {
            value?.let { preferences.edit().putInt(KEY_LEAGUE_ID, value).apply() }
        }
}