package com.example.rockpaperscissors.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Player (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "avatar")
    var avatar: Int,

    @ColumnInfo(name = "score")
    var score: Int,
)