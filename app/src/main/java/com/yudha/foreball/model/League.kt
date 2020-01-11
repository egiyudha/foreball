package com.yudha.foreball.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "league")
data class League(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "league_name")
    val leagueName: String
)