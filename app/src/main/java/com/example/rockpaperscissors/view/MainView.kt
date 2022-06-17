package com.example.rockpaperscissors.view

import android.content.Context
import com.example.rockpaperscissors.model.Player

interface MainView {
    fun context(): Context
    fun onResultDatabase(players: List<Player>)
}